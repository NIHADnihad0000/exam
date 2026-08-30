package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.StatutObjet;
import ma.airport.objetsperdus.repository.CorrespondanceRepository;
import ma.airport.objetsperdus.repository.DeclarationRepository;
import ma.airport.objetsperdus.repository.ObjetTrouveRepository;
import ma.airport.objetsperdus.repository.RestitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatistiqueService {

    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    private ObjetTrouveRepository objetTrouveRepository;

    @Autowired
    private RestitutionRepository restitutionRepository;

    @Autowired
    private CorrespondanceRepository correspondanceRepository;

    // Calculer et compiler l'ensemble des chiffres clés de l'aéroport
    public Map<String, Long> calculerRapportGlobal() {
        Map<String, Long> rapport = new HashMap<>();

        // 1. Nombre total de déclarations de perte déposées par les passagers
        rapport.put("nombreDeclarations", declarationRepository.count());

        // 2. Nombre total d'objets trouvés ramenés par les agents
        rapport.put("nombreObjetsTrouves", objetTrouveRepository.count());

        // 3. Nombre total d'objets officiellement restitués (remis à leur propriétaire)
        rapport.put("nombreObjetsRestitues", restitutionRepository.count());

        // 4. Nombre d'objets actuellement en attente au stock (Statut ENREGISTRE)
        long enAttente = objetTrouveRepository.findByStatut(StatutObjet.ENREGISTRE).size();
        rapport.put("nombreObjetsEnAttente", enAttente);

        // 5. Nombre de rapprochements/correspondances détectés par l'algorithme
        rapport.put("nombreCorrespondances", correspondanceRepository.count());

        return rapport;
    }
}
