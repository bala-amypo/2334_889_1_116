package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;
import java.util.stream.Collectors;

public class JwtTokenProvider {

    private String jwtSecret;
    private long jwtExpirationMs;

    public String generateToken(Long userId, String email, Set<String> roles) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", String.join(",", roles))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody();
    }
}
