package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Restitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestitutionRepository extends JpaRepository<Restitution, Long> {
}
