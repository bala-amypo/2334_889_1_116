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

@Component
public class JwtTokenProvider {

    // ❗ MUST NOT be static/final (tests inject value)
    private String jwtSecret = "default-test-secret-key-123456789012345678901234";

    private long jwtExpirationMs = 3600000L;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // ================= TOKEN GENERATION =================
    public String generateToken(Long userId, String email, Set<String> roles) {

        String rolesCsv = (roles == null || roles.isEmpty())
                ? ""
                : String.join(",", roles);

        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", rolesCsv)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ================= CLAIMS =================
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ================= VALIDATION =================
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ================= REQUIRED BY OTHER TESTS =================
    public String getUsername(String token) {
        return getClaims(token).get("email", String.class);
    }

    public Set<String> getRole(String token) {
        String rolesCsv = getClaims(token).get("roles", String.class);
        if (rolesCsv == null || rolesCsv.isEmpty()) {
            return Collections.emptySet();
        }
        return new HashSet<>(Arrays.asList(rolesCsv.split(",")));
    }
}
