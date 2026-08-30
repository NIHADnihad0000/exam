package ma.airport.objetsperdus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "droits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Droit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // Exemple: "LIRE_OBJET", "SUPPRIMER_DECLARATION"
}
