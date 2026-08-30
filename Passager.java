package ma.airport.objetsperdus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "passagers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false)
    private String nom;

    @NotBlank @Column(nullable = false)
    private String prenom;

    @NotBlank @Column(nullable = false)
    private String numeroPasseport;

    private String telephone;
    private String email;
}
