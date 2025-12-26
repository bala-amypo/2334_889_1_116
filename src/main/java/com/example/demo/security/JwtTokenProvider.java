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

//     // REQUIRED by reflection tests
//     private String jwtSecret = "default-test-secret-key-1234567890";

//     // REQUIRED by reflection tests
//     private long jwtExpirationMs = 3600000L;

//     private SecretKey getKey() {
//         return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//     }

//     public String generateToken(long userId, String email, Set<String> roles) {

//         // 🔑 Use ONE now reference
//         Date now = new Date();

//         Claims claims = Jwts.claims();
//         claims.setSubject(email);          // subject
//         claims.put("email", email);        // explicit email claim
//         claims.put("userId", userId);      // userId claim

//         // 🔑 roles → ROLE_ prefixed CSV, sorted, NO spaces
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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    // MUST be at least 32 characters for HS256
    private final String jwtSecret =
            "THIS_IS_A_DEFAULT_SECRET_KEY_ONLY_FOR_LOCAL_RUN_AND_TEST_OVERRIDE";

    private final long jwtExpirationMs = 24 * 60 * 60 * 1000; // 1 day

    /* ================= TOKEN GENERATION ================= */

    public String generateToken(Long userId, String email, Set<String> roles) {

        String rolesCsv = String.join(",", roles);

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", rolesCsv)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /* ================= CLAIM EXTRACTION ================= */

    public Long getUserIdFromJwt(String token) {
        return getAllClaims(token).get("userId", Long.class);
    }

    public String getEmailFromJwt(String token) {
        return getAllClaims(token).get("email", String.class);
    }

    public String getRolesFromJwt(String token) {
        return getAllClaims(token).get("roles", String.class);
    }

    /* ================= VALIDATION ================= */

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /* ================= INTERNAL ================= */

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
