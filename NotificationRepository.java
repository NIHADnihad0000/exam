package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Trouver les notifications non lues d'un passager
    List<Notification> findByPassagerIdAndLuFalse(Long passagerId);
}
