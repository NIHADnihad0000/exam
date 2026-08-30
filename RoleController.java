package ma.airport.objetsperdus.controller;

import jakarta.validation.Valid;
import ma.airport.objetsperdus.entity.Droit;
import ma.airport.objetsperdus.entity.Role;
import ma.airport.objetsperdus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin/roles-droits")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Récupérer tous les rôles avec leurs permissions : GET /api/admin/roles-droits/roles
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleService.listerTousLesRoles());
    }

    // Créer un nouveau droit système : POST /api/admin/roles-droits/droits
    @PostMapping("/droits")
    public ResponseEntity<?> ajouterDroit(@Valid @RequestBody Droit droit) {
        try {
            Droit nouveau = roleService.creerDroit(droit);
            return ResponseEntity.ok(nouveau);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Lister tous les droits existants : GET /api/admin/roles-droits/droits
    @GetMapping("/droits")
    public ResponseEntity<List<Droit>> getDroits() {
        return ResponseEntity.ok(roleService.listerTousLesDroits());
    }

    // Donner un droit à un rôle : PUT /api/admin/roles-droits/associer
    @PutMapping("/associer")
    public ResponseEntity<?> associerPermission(@RequestParam Long roleId, @RequestParam Long droitId) {
        try {
            Role roleMisAJour = roleService.ajouterDroitARole(roleId, droitId);
            return ResponseEntity.ok(roleMisAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Retirer un droit d'un rôle : DELETE /api/admin/roles-droits/retirer
    @DeleteMapping("/retirer")
    public ResponseEntity<?> retirerPermission(@RequestParam Long roleId, @RequestParam Long droitId) {
        try {
            Role roleMisAJour = roleService.supprimerDroitDeRole(roleId, droitId);
            return ResponseEntity.ok(roleMisAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
