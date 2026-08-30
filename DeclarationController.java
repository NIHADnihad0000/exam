package ma.airport.objetsperdus.controller;

import jakarta.validation.Valid;
import ma.airport.objetsperdus.entity.Declaration;
import ma.airport.objetsperdus.entity.StatutDeclaration;
import ma.airport.objetsperdus.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/declarations")
public class DeclarationController {

    @Autowired
    private DeclarationService declarationService;

    // Créer une déclaration : POST /api/declarations
    @PostMapping
    public ResponseEntity<Declaration> ajouterDeclaration(@Valid @RequestBody Declaration declaration) {
        Declaration nouvelle = declarationService.creerDeclaration(declaration);
        return ResponseEntity.ok(nouvelle);
    }

    // Consulter une déclaration par sa référence : GET /api/declarations/{reference}
    @GetMapping("/{reference}")
    public ResponseEntity<?> consulterParReference(@PathVariable String reference) {
        Optional<Declaration> od = declarationService.trouverParReference(reference);
        if (od.isEmpty()) {
            return ResponseEntity.status(404).body("Déclaration introuvable avec la référence : " + reference);
        }
        return ResponseEntity.ok(od.get());
    }

    // Mettre à jour le statut : PUT /api/declarations/{reference}/statut
    @PutMapping("/{reference}/statut")
    public ResponseEntity<?> changerStatut(@PathVariable String reference, @RequestParam StatutDeclaration statut) {
        try {
            Declaration miseAJour = declarationService.modifierStatut(reference, statut);
            return ResponseEntity.ok(miseAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
