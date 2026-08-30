package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.Notification;
import ma.airport.objetsperdus.entity.Passager;
import ma.airport.objetsperdus.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // 1. Créer et enregistrer une notification pour un passager
    public Notification creerNotification(Passager passager, String message) {
        Notification notification = new Notification();
        notification.setPassager(passager);
        notification.setMessage(message);
        notification.setDateEnvoi(LocalDateTime.now());
        notification.setLu(false); // Non lue par défaut

        return notificationRepository.save(notification);
    }

    // 2. Lister toutes les notifications d'un passager spécifique
    public List<Notification> listerNotificationsPassager(Long passagerId) {
        return notificationRepository.findByPassagerIdAndLuFalse(passagerId);
    }

    // 3. Marquer une notification comme lue
    public Notification marquerCommeLue(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Erreur : Notification introuvable avec l'ID : " + notificationId));
        notification.setLu(true);
        return notificationRepository.save(notification);
    }
}
