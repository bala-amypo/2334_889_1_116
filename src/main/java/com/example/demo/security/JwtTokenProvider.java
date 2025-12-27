// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.nio.charset.StandardCharsets;
// import java.util.*;
// import java.util.stream.Collectors;

// @Component
// public class JwtTokenProvider {
//     private String jwtSecret = "default-test-secret-key-1234567890";

//     private long jwtExpirationMs = 3600000L;

//     private SecretKey getKey() {
//         return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//     }

//     public String generateToken(long userId, String email, Set<String> roles) {

//         Date now = new Date();

//         Claims claims = Jwts.claims();
//         claims.setSubject(email);          
//         claims.put("email", email);       
//         claims.put("userId", userId);     

//         String rolesCsv = roles.stream()
//                 .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
//                 .sorted()
//                 .collect(Collectors.joining(","));

//         claims.put("roles", rolesCsv);

//         Date expiry = new Date(now.getTime() + jwtExpirationMs);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setIssuedAt(now)
//                 .setExpiration(expiry)
//                 .signWith(getKey(), SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                     .setSigningKey(getKey())
//                     .build()
//                     .parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public Claims getClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }







package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    // Requirements specify a secret key for HS256/HS512 [cite: 282]
    private String jwtSecret = "default-test-secret-key-1234567890-secure-padding";
    private long jwtExpirationMs = 3600000L;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // Required by Section 8.1 [cite: 278]
    public String generateToken(Long userId, String email, Set<String> roles) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        // Convert roles to a Comma-Separated Values (CSV) string for the "roles" claim
        // This addresses testJwtRolesCsv [cite: 278]
        String rolesCsv = roles.stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .sorted()
                .collect(Collectors.joining(","));

        // Creating custom claims map [cite: 278, 281]
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId); // Fixes testJwtContainsUserIdAndEmail
        claims.put("email", email);   // Fixes testJwtContainsUserIdAndEmail
        claims.put("roles", rolesCsv); // Fixes testJwtRolesCsv

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Required to extract username/email from token 
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Required to extract roles as a Set 
    public Set<String> getRole(String token) {
        String rolesCsv = getClaims(token).get("roles", String.class);
        if (rolesCsv == null || rolesCsv.isEmpty()) {
            return Collections.emptySet();
        }
        return Arrays.stream(rolesCsv.split(","))
                     .collect(Collectors.toSet());
    }

    // Required to parse and return token claims 
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Required to validate token signature and expiration [cite: 282]
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}