package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.Utilisateur;
import ma.airport.objetsperdus.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // 1. Ajouter un utilisateur
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new RuntimeException("Erreur : Un utilisateur existe déjà avec cet email.");
        }
        if (utilisateurRepository.findByUsername(utilisateur.getUsername()).isPresent()) {
            throw new RuntimeException("Erreur : Ce nom d'utilisateur est déjà pris.");
        }
        utilisateur.setActif(true); // Toujours actif à la création
        return utilisateurRepository.save(utilisateur);
    }

    // 2. Consulter tous les utilisateurs
    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // 3. Consulter un utilisateur par son ID
    public Optional<Utilisateur> trouverParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    // 4. Modifier un utilisateur
    public Utilisateur modifierUtilisateur(Long id, Utilisateur infosModifiees) {
        return utilisateurRepository.findById(id).map(user -> {
            user.setUsername(infosModifiees.getUsername());
            user.setEmail(infosModifiees.getEmail());
            user.setRole(infosModifiees.getRole());
            if (infosModifiees.getPassword() != null && !infosModifiees.getPassword().isBlank()) {
                user.setPassword(infosModifiees.getPassword()); // Met à jour le mot de passe s'il est fourni
            }
            return utilisateurRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Erreur : Utilisateur introuvable avec l'ID : " + id));
    }

    // 5. Supprimer un utilisateur
    public void supprimerUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Erreur : Impossible de supprimer, utilisateur introuvable.");
        }
        utilisateurRepository.deleteById(id);
    }

    // 6. Activer ou désactiver un utilisateur
    public Utilisateur changerStatutActivation(Long id, boolean actif) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erreur : Utilisateur introuvable avec l'ID : " + id));
        utilisateur.setActif(actif);
        return utilisateurRepository.save(utilisateur);
    }
}
