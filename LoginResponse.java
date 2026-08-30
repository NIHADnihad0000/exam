package ma.airport.objetsperdus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private String username;
    private String email;
    private String role; // Permet de savoir vers quel espace le rediriger
}

