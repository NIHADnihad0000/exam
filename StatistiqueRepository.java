package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistiqueRepository extends JpaRepository<Statistique, Long> {
    // Cette interface utilise les fonctions standards pour communiquer avec SQL Server
}
