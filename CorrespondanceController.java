package ma.airport.objetsperdus.controller;

import ma.airport.objetsperdus.entity.Correspondance;
import ma.airport.objetsperdus.service.CorrespondanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/api/correspondances")
public class CorrespondanceController {

    @Autowired
    private CorrespondanceService correspondanceService;

    // Lancer la recherche automatique : POST /api/correspondances/run?reference=OBJ-XXXX
    @PostMapping("/run")
    public ResponseEntity<List<Correspondance>> lancerRecherche(@RequestParam String reference) {
        List<Correspondance> liste = correspondanceService.chercherCorrespondancesPourDeclaration(reference);
        return ResponseEntity.ok(liste);
    }

    // Validation finale par l'agent : PUT /api/correspondances/{id}/valider
    @PutMapping("/{id}/valider")
    public ResponseEntity<?> validerRapprochement(@PathVariable Long id) {
        try {
            correspondanceService.validerCorrespondance(id);
            return ResponseEntity.ok("La correspondance a ete validee avec succes par l'agent !");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
