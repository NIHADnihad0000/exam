package ma.airport.objetsperdus.controller;

import jakarta.validation.Valid;
import ma.airport.objetsperdus.entity.Utilisateur;
import ma.airport.objetsperdus.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Ajouter : POST /api/admin/utilisateurs
    @PostMapping
    public ResponseEntity<?> creer(@Valid @RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur nouveau = utilisateurService.ajouterUtilisateur(utilisateur);
            return ResponseEntity.ok(nouveau);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Consulter tout : GET /api/admin/utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateur>> lister() {
        return ResponseEntity.ok(utilisateurService.listerUtilisateurs());
    }

    // Consulter un seul : GET /api/admin/utilisateurs/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getParId(@PathVariable Long id) {
        return utilisateurService.trouverParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Modifier : PUT /api/admin/utilisateurs/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id, @Valid @RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur misAJour = utilisateurService.modifierUtilisateur(id, utilisateur);
            return ResponseEntity.ok(misAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Supprimer : DELETE /api/admin/utilisateurs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        try {
            utilisateurService.supprimerUtilisateur(id);
            return ResponseEntity.ok("Utilisateur supprime avec succes !");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Activer / Désactiver : PATCH /api/admin/utilisateurs/{id}/activation
    @PatchMapping("/{id}/activation")
    public ResponseEntity<?> basculerActivation(@PathVariable Long id, @RequestParam boolean actif) {
        try {
            Utilisateur misAJour = utilisateurService.changerStatutActivation(id, actif);
            String message = actif ? "Le compte a ete active." : "Le compte a ete desactive.";
            return ResponseEntity.ok(misAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
