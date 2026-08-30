package ma.airport.objetsperdus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "correspondances")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Correspondance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private Declaration declaration;

    @NotNull @ManyToOne
    @JoinColumn(name = "objet_trouve_id", nullable = false)
    private ObjetTrouve objetTrouve;

    @NotNull @Column(nullable = false)
    private Double scoreCompatibilite; // Exemple: 0.85 pour 85% de ressemblance

    @NotNull @Column(nullable = false)
    private LocalDateTime dateCalcul;
}
