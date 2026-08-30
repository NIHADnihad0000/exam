package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.dto.LoginRequest;
import ma.airport.objetsperdus.dto.LoginResponse;
import ma.airport.objetsperdus.entity.Utilisateur;
import ma.airport.objetsperdus.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public LoginResponse authentifier(LoginRequest request) {
        // 1. Chercher l'utilisateur par son email
        Optional<Utilisateur> ou = utilisateurRepository.findByEmail(request.getEmail());

        // 2. Si l'email n'existe pas, on refuse l'accès
        if (ou.isEmpty()) {
            throw new RuntimeException("Erreur : Email ou mot de passe incorrect !");
        }

        Utilisateur utilisateur = ou.get();

        // 3. Vérifier le mot de passe (comparaison simple)
        if (!utilisateur.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Erreur : Email ou mot de passe incorrect !");
        }
        // Vérification de sécurité de l'Étape 11 : Le compte est-il actif ?
        if (!utilisateur.isActif()) {
            throw new RuntimeException("Erreur : Votre compte a été désactivé par l'administrateur !");
        }

        // 4. Si tout est correct, on retourne les infos de connexion avec le Rôle
        return new LoginResponse(
                "Authentification réussie",
                utilisateur.getUsername(),
                utilisateur.getEmail(),
                utilisateur.getRole().getNom().name() // Exemple: "ADMIN"
        );
    }
}
