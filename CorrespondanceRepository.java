package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Correspondance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CorrespondanceRepository extends JpaRepository<Correspondance, Long> {
    List<Correspondance> findByDeclarationId(Long declarationId);
}
