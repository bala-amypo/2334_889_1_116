package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;

public class JwtTokenProvider {

    String jwtSecret;
    Long jwtExpirationMs;

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
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
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
