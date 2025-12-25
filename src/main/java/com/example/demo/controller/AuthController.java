package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        return "registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return "token";
    }
}
