package ma.airport.objetsperdus.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permet la liaison directe avec ton Frontend
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Simulation d'authentification simple pour l'exercice
        if (("reda".equals(username) || "admin".equals(username)) && "123".equals(password)) {
            Map<String, Object> user = new HashMap<>();
            user.put("username", username);

            Map<String, String> role = new HashMap<>();
            role.put("nom", "admin".equals(username) ? "ADMIN" : "AGENT");
            user.put("role", role);

            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
    }
}
