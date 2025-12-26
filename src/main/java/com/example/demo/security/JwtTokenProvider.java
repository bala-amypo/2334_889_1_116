// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import java.util.Set;
// import java.util.stream.Collectors;

// @Component
// public class JwtTokenProvider {

    
//     private String jwtSecret =
//             "a-string-secret-at-least-256-bits-long";
//             // "THIS_IS_A_DEFAULT_SECRET_KEY_ONLY_FOR_LOCAL_RUN_AND_TEST_OVERRIDE_MUST_BE_AT_LEAST_64_CHARACTERS_LONG";

   
//     private long jwtExpirationMs = 3600000; 

//     private SecretKey getSigningKey() {
//         return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//     }

//     public String generateToken(Long userId, String email, Set<String> roles) {

//         String rolesCsv = (roles == null || roles.isEmpty())
//                 ? ""
//                 : roles.stream().collect(Collectors.joining(","));

//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("userId", userId)
//                 .claim("email", email)
//                 .claim("roles", rolesCsv)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                 .signWith(getSigningKey(), SignatureAlgorithm.HS512)
//                 .compact();
//     }
//     public Claims getClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSigningKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public boolean validateToken(String token) {
//         try {
//             getClaims(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }


package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // REQUIRED by reflection tests
    private String jwtSecret = "THIS_IS_A_VERY_SECURE_SECRET_KEY_FOR_JWT_123456";

    private final long validityInMillis = 86400000; // 1 day

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // ✅ EXACT signature expected by tests
    public String generateToken(long userId, String email, Set<String> roles) {

        Claims claims = Jwts.claims();
        claims.setSubject(email);                 // ✅ email as subject
        claims.put("userId", userId);             // ✅ userId claim

        // ✅ roles MUST be CSV string
        String rolesCsv = roles.stream()
                .sorted()
                .collect(Collectors.joining(","));
        claims.put("roles", rolesCsv);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Optional helper
    public String generateToken(String email) {
        return generateToken(1L, email, Set.of("USER"));
    }

    // ✅ MUST return false for invalid token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ REQUIRED by tests
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
