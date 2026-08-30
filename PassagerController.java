package ma.airport.objetsperdus.controller;

import jakarta.validation.Valid;
import ma.airport.objetsperdus.entity.Passager;
import ma.airport.objetsperdus.repository.PassagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/passagers")
public class PassagerController {

    @Autowired
    private PassagerRepository passagerRepository;

    // Ajouter un passager
    @PostMapping
    public ResponseEntity<Passager> ajouterPassager(
            @Valid @RequestBody Passager passager) {

        Passager nouveau = passagerRepository.save(passager);

        return ResponseEntity.ok(nouveau);
    }

    // Rechercher un passager par numéro de passeport
    @GetMapping("/passeport/{numeroPasseport}")
    public ResponseEntity<?> rechercherParPasseport(
            @PathVariable String numeroPasseport) {

        Optional<Passager> passager =
                passagerRepository.findByNumeroPasseport(numeroPasseport);

        if (passager.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(passager.get());
    }
}