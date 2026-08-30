package ma.airport.objetsperdus.controller;

import ma.airport.objetsperdus.entity.Notification;
import ma.airport.objetsperdus.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Consulter les notifications non lues d'un passager : GET /api/notifications/passager/{passagerId}
    @GetMapping("/passager/{passagerId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long passagerId) {
        List<Notification> liste = notificationService.listerNotificationsPassager(passagerId);
        return ResponseEntity.ok(liste);
    }

    // Passer une alerte en statut "lue" : PUT /api/notifications/{id}/lire
    @PutMapping("/{id}/lire")
    public ResponseEntity<?> lireNotification(@PathVariable Long id) {
        try {
            Notification miseAJour = notificationService.marquerCommeLue(id);
            return ResponseEntity.ok(miseAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
