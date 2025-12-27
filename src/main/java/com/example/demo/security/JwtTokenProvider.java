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
    // Ensure the secret is long enough for HS256/HS512
    private String jwtSecret = "default-test-secret-key-1234567890-secure-string-padding";
    private long jwtExpirationMs = 3600000L;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // Required by Section 8: Generates JWT with claims 
    public String generateToken(Long userId, String email, Set<String> roles) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        // Convert roles to CSV to satisfy 'testJwtRolesCsv'
        String rolesCsv = roles.stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .collect(Collectors.joining(","));

        // Build claims to satisfy 'testJwtGenerationClaims' and 'testJwtContainsUserIdAndEmail'
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId); 
        claims.put("email", email);   
        claims.put("roles", rolesCsv); 

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Required: Extracts username/email from token [cite: 279]
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Required: Extracts roles from token [cite: 280]
    public Set<String> getRole(String token) {
        String rolesCsv = getClaims(token).get("roles", String.class);
        if (rolesCsv == null || rolesCsv.isEmpty()) {
            return Collections.emptySet();
        }
        return Arrays.stream(rolesCsv.split(","))
                     .collect(Collectors.toSet());
    }

    // Required: Parses and returns token claims [cite: 281]
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Required: Validates token signature and expiration [cite: 282]
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