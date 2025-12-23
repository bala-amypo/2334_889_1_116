package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // fetch user from DB
        User existingUser = userService.getUserById(user.getId()); // or by email

        // generate token with correct parameters
        Set<String> roles = existingUser.getRoles(); // assuming Set<String>
        String token = jwtTokenProvider.generateToken(
                existingUser.getId(),    // Long
                existingUser.getEmail(), // String
                roles                    // Set<String>
        );

        return token;
    }
}

