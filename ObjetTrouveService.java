package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.ObjetTrouve;
import ma.airport.objetsperdus.entity.StatutObjet;
import ma.airport.objetsperdus.repository.ObjetTrouveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ObjetTrouveService {

    @Autowired
    private ObjetTrouveRepository objetTrouveRepository;

    // 1. Enregistrer un nouvel objet trouvé
    public ObjetTrouve enregistrerObjet(ObjetTrouve objet) {
        objet.setStatut(StatutObjet.ENREGISTRE); // Statut initial obligatoire
        return objetTrouveRepository.save(objet);
    }

    // 2. Consulter tous les objets trouvés
    public List<ObjetTrouve> listerTousLesObjets() {
        return objetTrouveRepository.findAll();
    }

    // 3. Consulter un seul objet par son ID
    public Optional<ObjetTrouve> trouverParId(Long id) {
        return objetTrouveRepository.findById(id);
    }

    // 4. Modifier un objet complet (description, emplacement, etc.)
    public ObjetTrouve modifierObjet(Long id, ObjetTrouve objetModifie) {
        return objetTrouveRepository.findById(id).map(objet -> {
            objet.setDescription(objetModifie.getDescription());
            objet.setCategorie(objetModifie.getCategorie());
            objet.setDateTrouve(objetModifie.getDateTrouve());
            objet.setLieuTrouve(objetModifie.getLieuTrouve());
            objet.setEmplacementStockage(objetModifie.getEmplacementStockage());
            return objetTrouveRepository.save(objet);
        }).orElseThrow(() -> new RuntimeException("Erreur : Objet introuvable avec l'ID : " + id));
    }

    // 5. Mettre à jour uniquement le statut d'un objet
    public ObjetTrouve mettreAJourStatut(Long id, StatutObjet nouveauStatut) {
        ObjetTrouve objet = objetTrouveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erreur : Objet introuvable avec l'ID : " + id));
        objet.setStatut(nouveauStatut);
        return objetTrouveRepository.save(objet);
    }
}
