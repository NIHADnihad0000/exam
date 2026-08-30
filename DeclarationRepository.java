package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

    // Fonction indispensable pour retrouver une déclaration avec son code unique
    Optional<Declaration> findByReference(String reference);
}
