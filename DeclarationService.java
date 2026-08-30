package ma.airport.objetsperdus.service;

import ma.airport.objetsperdus.entity.Declaration;
import ma.airport.objetsperdus.entity.StatutDeclaration;
import ma.airport.objetsperdus.repository.DeclarationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeclarationService {

    @Autowired
    private DeclarationRepository declarationRepository;

    // 1. Créer une déclaration et générer sa référence automatique
    public Declaration creerDeclaration(Declaration declaration) {
        // Génère la date sous format YYYYMMDD
        String datePart = declaration.getDatePerte().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Génère 4 lettres/chiffres au hasard
        String randomPart = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        // Assemblage final : OBJ-20260820-A52F
        String referenceGeneree = "OBJ-" + datePart + "-" + randomPart;

        declaration.setReference(referenceGeneree);
        declaration.setStatut(StatutDeclaration.EN_COURS);

        return declarationRepository.save(declaration);
    }

    // 2. Consulter une déclaration par sa référence unique
    public Optional<Declaration> trouverParReference(String reference) {
        return declarationRepository.findByReference(reference);
    }

    // 3. Mettre à jour le statut d'une déclaration
    public Declaration modifierStatut(String reference, StatutDeclaration nouveauStatut) {
        Optional<Declaration> od = declarationRepository.findByReference(reference);
        if (od.isEmpty()) {
            throw new RuntimeException("Erreur : Aucune déclaration trouvée avec la référence : " + reference);
        }
        Declaration declaration = od.get();
        declaration.setStatut(nouveauStatut);
        return declarationRepository.save(declaration);
    }
}
