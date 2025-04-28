package com.ia.demo.api.v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/v1/login")
public class LoginController {

    @PostMapping()
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentification simulée
        if ("admin".equals(username) && "admin".equals(password)) {
            request.getSession().setAttribute("user", username);
            return "forward:/WEB-INF/views/index.html";
        } else {
            return "redirect:/login.jsp?error=true";
        }
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}