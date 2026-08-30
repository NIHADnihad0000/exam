package ma.airport.objetsperdus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "statistiques")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Column(nullable = false)
    private LocalDate dateRapport;

    @Column(nullable = false)
    private int totalObjetsTrouves;

    @Column(nullable = false)
    private int totalObjetsRestitues;

    @Column(nullable = false)
    private double tauxRestitution; // Pourcentage d'objets rendus
}
