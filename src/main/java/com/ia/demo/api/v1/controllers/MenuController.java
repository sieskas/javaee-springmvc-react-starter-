package com.ia.demo.api.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getUserMenu(HttpServletRequest request) {
        // TODO: Récupérer les rôles de l'utilisateur authentifié depuis la session
        // Pour l'instant, simulons un utilisateur avec ROLE_ADMIN
        List<String> userRoles = Collections.singletonList("ROLE_ADMIN");

        // Récupérer le menu selon les rôles
        List<Map<String, Object>> menu = getMenuForRoles(userRoles);

        return ResponseEntity.ok(menu);
    }

    private List<Map<String, Object>> getMenuForRoles(List<String> roles) {
        List<Map<String, Object>> menu = new ArrayList<>();

        // Menu Dashboard - accessible à tous les utilisateurs
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("id", 1);
        dashboard.put("code", "dashboard");
        dashboard.put("label", "Tableau de bord");
        dashboard.put("url", "/dashboard");
        dashboard.put("icon", "home");
        dashboard.put("displayOrder", 1);
        dashboard.put("requiredPermissions", Collections.emptyList());
        dashboard.put("children", new ArrayList<>());

        // Menu Utilisateurs - accessible uniquement aux administrateurs
        Map<String, Object> users = new HashMap<>();
        users.put("id", 2);
        users.put("code", "users");
        users.put("label", "Utilisateurs");
        users.put("url", "/dashboard/users");
        users.put("icon", "users");
        users.put("displayOrder", 2);
        users.put("requiredPermissions", Collections.singletonList("ROLE_ADMIN"));
        users.put("children", new ArrayList<>());

        // Menu Rapports - accessible à tous les utilisateurs
        Map<String, Object> reports = new HashMap<>();
        reports.put("id", 3);
        reports.put("code", "reports");
        reports.put("label", "Rapports");
        reports.put("url", "/dashboard/reports");
        reports.put("icon", "bar-chart");
        reports.put("displayOrder", 3);
        reports.put("requiredPermissions", Collections.emptyList());
        reports.put("children", new ArrayList<>());

        // Menu Paramètres - accessible uniquement aux administrateurs
        Map<String, Object> settings = new HashMap<>();
        settings.put("id", 4);
        settings.put("code", "settings");
        settings.put("label", "Paramètres");
        settings.put("url", "/dashboard/settings");
        settings.put("icon", "settings");
        settings.put("displayOrder", 4);
        settings.put("requiredPermissions", Collections.singletonList("ROLE_ADMIN"));

        // Sous-menus pour Paramètres
        List<Map<String, Object>> settingsChildren = new ArrayList<>();

        Map<String, Object> generalSettings = new HashMap<>();
        generalSettings.put("id", 5);
        generalSettings.put("code", "general-settings");
        generalSettings.put("label", "Paramètres généraux");
        generalSettings.put("url", "/dashboard/settings/general");
        generalSettings.put("icon", "sliders");
        generalSettings.put("displayOrder", 1);
        generalSettings.put("requiredPermissions", Collections.singletonList("ROLE_ADMIN"));
        generalSettings.put("children", new ArrayList<>());

        Map<String, Object> securitySettings = new HashMap<>();
        securitySettings.put("id", 6);
        securitySettings.put("code", "security-settings");
        securitySettings.put("label", "Sécurité");
        securitySettings.put("url", "/dashboard/settings/security");
        securitySettings.put("icon", "shield");
        securitySettings.put("displayOrder", 2);
        securitySettings.put("requiredPermissions", Collections.singletonList("ROLE_ADMIN"));
        securitySettings.put("children", new ArrayList<>());

        settingsChildren.add(generalSettings);
        settingsChildren.add(securitySettings);

        settings.put("children", settingsChildren);

        // Ajouter tous les éléments au menu principal
        menu.add(dashboard);

        // Filtrer les éléments selon les rôles
        if (roles.contains("ROLE_ADMIN")) {
            menu.add(users);
        }

        menu.add(reports);

        if (roles.contains("ROLE_ADMIN")) {
            menu.add(settings);
        }

        return menu;
    }
}