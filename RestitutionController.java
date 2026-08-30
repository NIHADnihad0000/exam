package ma.airport.objetsperdus.controller;

import ma.airport.objetsperdus.entity.Restitution;
import ma.airport.objetsperdus.entity.Statistique;
import ma.airport.objetsperdus.service.RestitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/restitutions")
public class RestitutionController {

    @Autowired
    private RestitutionService restitutionService;

    // Déclencher la vérification et la restitution : POST /api/restitutions/valider
    @PostMapping("/valider")
    public ResponseEntity<?> validerRestitution(
            @RequestParam String reference,
            @RequestParam String numeroPieceIdentite,
            @RequestParam String agentResponsable) {
        try {
            Restitution resultat = restitutionService.restituerObjet(reference, numeroPieceIdentite, agentResponsable);
            return ResponseEntity.ok(resultat); // Cas normal : Code 200 et détails renvoyés
        } catch (RuntimeException e) {
            // Cas anormal : Identifiants ou pièces invalides -> Accès refusé
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Consulter le tableau de bord des statistiques : GET /api/restitutions/stats
    @GetMapping("/stats")
    public ResponseEntity<List<Statistique>> getDashboardStats() {
        return ResponseEntity.ok(restitutionService.obtenirToutesLesStatistiques());
    }
}
