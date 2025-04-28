package com.ia.demo.api.v1.controllers;

import com.ia.demo.api.v1.resources.UserDTO;
import com.ia.demo.domain.User;
import com.ia.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        System.out.println("AuthController initialisé sur le mapping: /api/auth");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        boolean isAuthenticated = authService.authenticate(username, password, request);

        if (isAuthenticated) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Authentification réussie");

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Identifiants invalides");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkAuth(HttpServletRequest request) {
        if (authService.isAuthenticated(request)) {
            Optional<User> userOpt = authService.getCurrentUser(request);

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                UserDTO userDTO = convertToDTO(user);

                Map<String, Object> response = new HashMap<>();
                response.put("authenticated", true);
                response.put("user", userDTO);

                return ResponseEntity.ok(response);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", false);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Déconnexion réussie");

        return ResponseEntity.ok(response);
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername()).build();
    }
}