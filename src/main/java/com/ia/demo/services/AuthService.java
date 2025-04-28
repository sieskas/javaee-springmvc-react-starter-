package com.ia.demo.services;

import com.ia.demo.domain.User;
import com.ia.demo.outcall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authentifie un utilisateur et crée une session si les identifiants sont valides
     */
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, user.getPassword())) {
                // Créer la session
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("authenticated", true);
                session.setMaxInactiveInterval(3600); // 1 heure

                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie si l'utilisateur est authentifié
     */
    public boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && Boolean.TRUE.equals(session.getAttribute("authenticated"));
    }

    /**
     * Récupère l'utilisateur connecté
     */
    public Optional<User> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null && Boolean.TRUE.equals(session.getAttribute("authenticated"))) {
            Long userId = (Long) session.getAttribute("userId");
            return userRepository.findById(userId);
        }

        return Optional.empty();
    }

    /**
     * Déconnecte l'utilisateur en invalidant la session
     */
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}