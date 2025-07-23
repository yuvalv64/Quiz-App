package com.quiz.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "🔓 This endpoint is PUBLIC!";
    }

    @GetMapping("/protected")
    public String protectedEndpoint(Authentication authentication) {
        return "🔒 Hello, " + authentication.getName() + " - You are AUTHENTICATED!";
    }
}
