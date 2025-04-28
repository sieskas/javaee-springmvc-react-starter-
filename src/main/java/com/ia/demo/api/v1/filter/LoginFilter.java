package com.ia.demo.api.v1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // Pas besoin d'initialisation ici
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // Laisser passer les assets directement
        if (path.startsWith("/assets")) {
            chain.doFilter(request, response);
            return;
        }

        Object user = req.getSession().getAttribute("user");

        if (path.equals("/login.jsp")) {
            if (user != null) {
                req.getRequestDispatcher("/WEB-INF/views/index.html").forward(req, res);
                return;
            } else {
                chain.doFilter(request, response);
                return;
            }
        }
        if (path.startsWith("/api/v1/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/api/")) {
            if (user == null) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            } else {
                chain.doFilter(request, response); // utilisateur valide, continuer
            }
            return;
        }

        // Traitement général
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.getRequestDispatcher("/WEB-INF/views/index.html").forward(req, res);
        }
    }

    @Override
    public void destroy() {
        // Rien à détruire
    }
}