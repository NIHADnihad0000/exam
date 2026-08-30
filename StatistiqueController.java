package ma.airport.objetsperdus.controller;

import ma.airport.objetsperdus.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/statistiques")
public class StatistiqueController {

    @Autowired
    private StatistiqueService statistiqueService;

    // Consulter le rapport de performance en temps réel : GET /api/statistiques
    @GetMapping
    public ResponseEntity<Map<String, Long>> obtenirRapportTableauDeBord() {
        Map<String, Long> statistiques = statistiqueService.calculerRapportGlobal();
        return ResponseEntity.ok(statistiques);
    }
}
