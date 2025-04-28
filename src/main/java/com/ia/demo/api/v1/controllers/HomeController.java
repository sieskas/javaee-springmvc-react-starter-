package com.ia.demo.api.v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({ "", "/" })
    public String index() {
        return "forward:/WEB-INF/views/index.html";
    }
}