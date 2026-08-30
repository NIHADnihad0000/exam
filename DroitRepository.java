package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Droit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DroitRepository extends JpaRepository<Droit, Long> {
    Optional<Droit> findByNom(String nom);
}
