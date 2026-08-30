package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.Droit;
import ma.airport.objetsperdus.entity.Role;
import ma.airport.objetsperdus.repository.DroitRepository;
import ma.airport.objetsperdus.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DroitRepository droitRepository;

    // 1. Consulter tous les rôles (ADMINISTRATEUR, AGENT_AIRPORT, etc.)
    public List<Role> listerTousLesRoles() {
        return roleRepository.findAll();
    }

    // 2. Créer un nouveau droit d'accès dans l'application (ex: "SUPPRIMER_DECLARATION")
    public Droit creerDroit(Droit droit) {
        if (droitRepository.findByNom(droit.getNom()).isPresent()) {
            throw new RuntimeException("Erreur : Ce droit existe deja !");
        }
        return droitRepository.save(droit);
    }

    // 3. Consulter tous les droits disponibles
    public List<Droit> listerTousLesDroits() {
        return droitRepository.findAll();
    }

    // 4. Assigner un droit spécifique à un rôle (L'essentiel de la gestion d'accès)
    public Role ajouterDroitARole(Long roleId, Long droitId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Erreur : Role introuvable"));

        Droit droit = droitRepository.findById(droitId)
                .orElseThrow(() -> new RuntimeException("Erreur : Droit introuvable"));

        // On ajoute le droit à la liste du rôle (la liaison ManyToMany se met à jour)
        if (!role.getDroits().contains(droit)) {
            role.getDroits().add(droit);
        }

        return roleRepository.save(role);
    }

    // 5. Retirer un droit d'un rôle
    public Role supprimerDroitDeRole(Long roleId, Long droitId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Erreur : Role introuvable"));

        Droit droit = droitRepository.findById(droitId)
                .orElseThrow(() -> new RuntimeException("Erreur : Droit introuvable"));

        role.getDroits().remove(droit);
        return roleRepository.save(role);
    }
}
