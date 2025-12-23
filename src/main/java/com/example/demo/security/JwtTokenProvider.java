package com.example.demo.security;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JwtTokenProvider {

    private String jwtSecret;
    private Long jwtExpirationMs;

    public String generateToken(Long userId, String email, Set<String> roles) {
        return Base64.getEncoder()
                .encodeToString((userId + "|" + email + "|" + String.join(",", roles)).getBytes());
    }

    public boolean validateToken(String token) {
        try {
            Base64.getDecoder().decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Object> getClaims(String token) {
        String decoded = new String(Base64.getDecoder().decode(token));
        String[] parts = decoded.split("\\|");

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", parts[0]);
        claims.put("email", parts[1]);
        claims.put("roles", parts.length > 2 ? parts[2] : "");
        return claims;
    }
}
