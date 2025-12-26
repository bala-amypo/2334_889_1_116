
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {

        String token = jwtTokenProvider.generateToken(
                1L,
                request.getEmail(),
                Set.of("USER")
        );

        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String token = jwtTokenProvider.generateToken(
                1L,
                request.getEmail(),
                Set.of("USER")
        );
        return new AuthResponse(token);
    }
}
