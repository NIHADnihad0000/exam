package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.Declaration;
import ma.airport.objetsperdus.entity.ObjetTrouve;
import ma.airport.objetsperdus.entity.Restitution;
import ma.airport.objetsperdus.entity.StatutObjet;
import ma.airport.objetsperdus.entity.StatutDeclaration;
import ma.airport.objetsperdus.entity.Statistique;
import ma.airport.objetsperdus.repository.DeclarationRepository;
import ma.airport.objetsperdus.repository.ObjetTrouveRepository;
import ma.airport.objetsperdus.repository.RestitutionRepository;
import ma.airport.objetsperdus.repository.StatistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestitutionService {

    @Autowired
    private RestitutionRepository restitutionRepository;

    @Autowired
    private ObjetTrouveRepository objetTrouveRepository;

    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    private StatistiqueRepository statistiqueRepository;

    @Autowired
    private NotificationService notificationService;

    // Enregistrer une restitution officielle après vérifications de sécurité
    public Restitution restituerObjet(String reference, String numeroPieceIdentite, String agentResponsable) {

        // 1. CAS ANORMAL : Référence incorrecte ou introuvable
        Declaration declaration = declarationRepository.findByReference(reference)
                .orElseThrow(() -> new RuntimeException("Restitution refusee : Reference de declaration incorrecte !"));

        // 2. CAS ANORMAL : Pièce d'identité non valide (ne correspond pas au passager)
        String passeportPassager = declaration.getPassager().getNumeroPasseport();
        if (!passeportPassager.equalsIgnoreCase(numeroPieceIdentite)) {
            throw new RuntimeException("Restitution refusee : Piece d'identite non valide ou ne correspond pas au proprietaire !");
        }

        // 3. CAS NORMAL : Vérification réussie -> On récupère l'objet associé
        // Pour ce flux, on considère que la déclaration a été rapprochée d'un objet trouvé via la correspondance
        if (declaration.getStatut() != StatutDeclaration.TROUVE) {
            throw new RuntimeException("Restitution impossible : Aucun objet validé n'est prêt pour cette déclaration.");
        }

        // On cherche un objet en statut CORRESPONDANCE lié de préférence (simulation de récupération du premier objet lié)
        List<ObjetTrouve> objetsTrouves = objetTrouveRepository.findByStatut(StatutObjet.CORRESPONDANCE);
        if (objetsTrouves.isEmpty()) {
            throw new RuntimeException("Restitution impossible : Aucun objet en stock n'est marqué en correspondance.");
        }

        ObjetTrouve objet = objetsTrouves.get(0); // Récupération de l'objet validé

        // Évolution des statuts
        objet.setStatut(StatutObjet.RESTITUE);
        objetTrouveRepository.save(objet);

        declaration.setStatut(StatutDeclaration.RESOLU);
        declarationRepository.save(declaration);

        // Enregistrement de la Restitution
        Restitution restitution = new Restitution();
        restitution.setObjetTrouve(objet);
        restitution.setPassager(declaration.getPassager());
        restitution.setDateRestitution(LocalDateTime.now());
        restitution.setAgentResponsable(agentResponsable);
        Restitution sauvegarde = restitutionRepository.save(restitution);

        // Envoi automatique de la notification finale au passager
        String messageNotification = "Votre objet lié à la déclaration " + reference + " vous a été officiellement restitué au guichet. Merci de votre confiance.";
        notificationService.creerNotification(declaration.getPassager(), messageNotification);

        // Mise à jour des statistiques du jour
        mettreAJourStatistiquesDuJour();

        return sauvegarde;
    }

    private void mettreAJourStatistiquesDuJour() {
        LocalDate aujourdHui = LocalDate.now();
        int trouves = (int) objetTrouveRepository.count();
        int restitues = (int) restitutionRepository.count();

        Statistique stats = statistiqueRepository.findAll().stream()
                .filter(s -> s.getDateRapport().equals(aujourdHui))
                .findFirst()
                .orElse(new Statistique());

        stats.setDateRapport(aujourdHui);
        stats.setTotalObjetsTrouves(trouves);
        stats.setTotalObjetsRestitues(restitues);

        if (trouves > 0) {
            stats.setTauxRestitution(((double) restitues / trouves) * 100);
        } else {
            stats.setTauxRestitution(0.0);
        }

        statistiqueRepository.save(stats);
    }

    public List<Statistique> obtenirToutesLesStatistiques() {
        return statistiqueRepository.findAll();
    }
}
