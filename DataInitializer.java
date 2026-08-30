package ma.airport.objetsperdus.config;

import ma.airport.objetsperdus.entity.Role;
import ma.airport.objetsperdus.entity.TypeRole;
import ma.airport.objetsperdus.entity.Utilisateur;
import ma.airport.objetsperdus.repository.RoleRepository;
import ma.airport.objetsperdus.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, UtilisateurRepository utilisateurRepository) {
        return args -> {
            // 1. Si le rôle AGENT_AIRPORT n'existe pas, on le crée via Java
            if (roleRepository.findByNom(TypeRole.AGENT_AIRPORT).isEmpty()) {
                Role agentRole = new Role();
                agentRole.setNom(TypeRole.AGENT_AIRPORT);
                roleRepository.save(agentRole);

                // 2. On crée l'utilisateur de test lié à ce rôle
                if (utilisateurRepository.findByEmail("reda@airport.ma").isEmpty()) {
                    Utilisateur u = new Utilisateur();
                    u.setUsername("reda");
                    u.setPassword("motdepasse123");
                    u.setEmail("reda@airport.ma");
                    u.setRole(agentRole); // L'association se fait automatiquement !

                    utilisateurRepository.save(u);
                    System.out.println(">>> [SUCCESS] Utilisateur de test 'reda' créé avec succès !");
                }
            }
        };
    }
}
