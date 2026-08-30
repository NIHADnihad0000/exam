package ma.airport.objetsperdus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "restitutions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @OneToOne
    @JoinColumn(name = "objet_trouve_id", nullable = false)
    private ObjetTrouve objetTrouve;

    @NotNull @ManyToOne
    @JoinColumn(name = "passager_id", nullable = false)
    private Passager passager;

    @NotNull @Column(nullable = false)
    private LocalDateTime dateRestitution;

    @NotBlank @Column(nullable = false)
    private String agentResponsable; // Nom de l'agent qui a rendu l'objet
}
