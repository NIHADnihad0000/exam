package ma.airport.objetsperdus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "objets_trouves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjetTrouve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false)
    private String description;

    @NotBlank @Column(nullable = false)
    private String categorie;

    @NotNull @Column(nullable = false)
    private LocalDateTime dateTrouve;

    @NotBlank @Column(nullable = false)
    private String lieuTrouve;

    @NotBlank @Column(nullable = false)
    private String emplacementStockage; // Exemple: "Casier A-12"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutObjet statut = StatutObjet.ENREGISTRE;
}
