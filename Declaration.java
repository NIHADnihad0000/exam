package ma.airport.objetsperdus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "declarations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nouvelle colonne pour stocker la référence unique générée
    @Column(unique = true, nullable = false)
    private String reference;

    @NotBlank @Column(nullable = false)
    private String description;

    @NotBlank @Column(nullable = false)
    private String categorie;

    @NotNull @Column(nullable = false)
    private LocalDateTime datePerte;

    @NotBlank @Column(nullable = false)
    private String lieuPerte;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDeclaration statut = StatutDeclaration.EN_COURS;

    @ManyToOne
    @JoinColumn(name = "passager_id", nullable = false)
    private Passager passager;
}
