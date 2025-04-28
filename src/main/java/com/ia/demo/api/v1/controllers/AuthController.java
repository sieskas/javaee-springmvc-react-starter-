package com.ia.demo.api.v1.controllers;

import com.ia.demo.api.v1.resources.UserDTO;
import com.ia.demo.domain.User;
import com.ia.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        System.out.println("AuthController initialisé sur le mapping: /api/auth");
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        User user = authService.validateCredentials(username, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("authenticated", true);
            session.setMaxInactiveInterval(3600); // 1 heure
            return "forward:/WEB-INF/views/index.html";
        } else {
            return "redirect:/login.jsp?error=true";
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
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Logout Successful");

        return ResponseEntity.ok(response);
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername()).build();
    }
}