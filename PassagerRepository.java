package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Passager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PassagerRepository extends JpaRepository<Passager, Long> {
    // Pratique pour retrouver un passager à l'aide de son passeport au guichet
    Optional<Passager> findByNumeroPasseport(String numeroPasseport);
}
