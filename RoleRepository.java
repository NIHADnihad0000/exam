package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Role;
import ma.airport.objetsperdus.entity.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(TypeRole nom);
}
