package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Utile si vous avez besoin de chercher par le nom d'utilisateur
    Optional<Utilisateur> findByUsername(String username);

    // Indispensable pour l'étape d'authentification par email
    Optional<Utilisateur> findByEmail(String email);
}
