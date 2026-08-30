package ma.airport.objetsperdus.controller;

import jakarta.validation.Valid;
import ma.airport.objetsperdus.entity.ObjetTrouve;
import ma.airport.objetsperdus.entity.StatutObjet;
import ma.airport.objetsperdus.service.ObjetTrouveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/objets-trouves")
public class ObjetTrouveController {

    @Autowired
    private ObjetTrouveService objetTrouveService;

    // Enregistrer : POST /api/objets-trouves
    @PostMapping
    public ResponseEntity<ObjetTrouve> ajouterObjet(@Valid @RequestBody ObjetTrouve objet) {
        ObjetTrouve nouveau = objetTrouveService.enregistrerObjet(objet);
        return ResponseEntity.ok(nouveau);
    }

    // Consulter tout : GET /api/objets-trouves
    @GetMapping
    public ResponseEntity<List<ObjetTrouve>> recupererTousLesObjets() {
        return ResponseEntity.ok(objetTrouveService.listerTousLesObjets());
    }

    // Consulter un seul : GET /api/objets-trouves/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> recupererParId(@PathVariable Long id) {
        Optional<ObjetTrouve> oo = objetTrouveService.trouverParId(id);
        if (oo.isEmpty()) {
            return ResponseEntity.status(404).body("Objet introuvable avec l'ID : " + id);
        }
        return ResponseEntity.ok(oo.get());
    }

    // Modifier un objet : PUT /api/objets-trouves/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> modifierObjet(@PathVariable Long id, @Valid @RequestBody ObjetTrouve objetModifie) {
        try {
            ObjetTrouve misAJour = objetTrouveService.modifierObjet(id, objetModifie);
            return ResponseEntity.ok(misAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Mettre à jour le statut : PATCH /api/objets-trouves/{id}/statut
    @PatchMapping("/{id}/statut")
    public ResponseEntity<?> changerStatut(@PathVariable Long id, @RequestParam StatutObjet statut) {
        try {
            ObjetTrouve misAJour = objetTrouveService.mettreAJourStatut(id, statut);
            return ResponseEntity.ok(misAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
