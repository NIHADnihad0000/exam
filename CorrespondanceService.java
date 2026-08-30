package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.Correspondance;
import ma.airport.objetsperdus.entity.Declaration;
import ma.airport.objetsperdus.entity.ObjetTrouve;
import ma.airport.objetsperdus.entity.StatutObjet;
import ma.airport.objetsperdus.repository.CorrespondanceRepository;
import ma.airport.objetsperdus.repository.DeclarationRepository;
import ma.airport.objetsperdus.repository.ObjetTrouveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorrespondanceService {

    @Autowired
    private CorrespondanceRepository correspondanceRepository;

    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    private ObjetTrouveRepository objetTrouveRepository;

    @Autowired
    private NotificationService notificationService;

    // 1. Algorithme de recherche automatique pour une déclaration
    public List<Correspondance> chercherCorrespondancesPourDeclaration(String reference) {
        Declaration dec = declarationRepository.findByReference(reference)
                .orElseThrow(() -> new RuntimeException("Déclaration introuvable"));

        // On récupère tous les objets actuellement enregistrés en stock
        List<ObjetTrouve> objetsPotentiels = objetTrouveRepository.findByStatut(StatutObjet.ENREGISTRE);
        List<Correspondance> resultats = new ArrayList<>();

        for (ObjetTrouve obj : objetsPotentiels) {
            // Règle 1 : La catégorie doit être la même
            if (dec.getCategorie().equalsIgnoreCase(obj.getCategorie())) {

                // Calcul du score basé sur les mots communs dans la description
                double score = calculerScoreDescription(dec.getDescription(), obj.getDescription());

                // Règle 2 : Bonus si le lieu est identique
                if (dec.getLieuPerte().equalsIgnoreCase(obj.getLieuTrouve())) {
                    score += 0.15;
                }

                // Si le score dépasse 30% de ressemblance, on enregistre la correspondance potentielle
                if (score > 0.30) {
                    if (score > 1.0) score = 1.0; // Capé à 100%

                    Correspondance corres = new Correspondance();
                    corres.setDeclaration(dec);
                    corres.setObjetTrouve(obj);
                    corres.setScoreCompatibilite(score);
                    corres.setDateCalcul(LocalDateTime.now());

                    resultats.add(correspondanceRepository.save(corres));
                }
            }
        }
        return resultats;
    }

    // 2. Validation par l'agent : l'agent confirme que c'est le bon objet
    public void validerCorrespondance(Long correspondanceId) {
        Correspondance corres = correspondanceRepository.findById(correspondanceId)
                .orElseThrow(() -> new RuntimeException("Correspondance introuvable"));

        // On change le statut de l'objet trouvé
        ObjetTrouve obj = corres.getObjetTrouve();
        obj.setStatut(StatutObjet.CORRESPONDANCE);
        objetTrouveRepository.save(obj);

        // On met à jour le statut de la déclaration
        Declaration dec = corres.getDeclaration();
        dec.setStatut(ma.airport.objetsperdus.entity.StatutDeclaration.TROUVE);
        declarationRepository.save(dec);

        // Envoi automatique de la notification au passager
        String messageAlerte = "Bonne nouvelle ! Un objet correspondant a votre declaration " + dec.getReference() + " a ete trouve. Veuillez vous presenter au guichet de l'aeroport.";
        notificationService.creerNotification(dec.getPassager(), messageAlerte);
    }

    // Méthode utilitaire pour comparer les textes
    private double calculerScoreDescription(String text1, String text2) {
        if (text1 == null || text2 == null) return 0.0;
        String[] mots1 = text1.toLowerCase().split(" ");
        String[] mots2 = text2.toLowerCase().split(" ");
        long communs = 0;

        for (String m1 : mots1) {
            if (m1.length() > 3) { // On ignore les petits mots
                for (String m2 : mots2) {
                    if (m1.equals(m2)) {
                        communs++;
                        break;
                    }
                }
            }
        }
        if (mots1.length == 0) return 0.0;
        return (double) communs / Math.min(mots1.length, mots2.length);
    }
}
