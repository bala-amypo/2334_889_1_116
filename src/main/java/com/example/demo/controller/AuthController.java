// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @PostMapping("/login")
//     public AuthResponse login(@RequestBody AuthRequest request) {
//         return new AuthResponse("dummy-token");
//     }

//     @PostMapping("/register")
//     public AuthResponse register(@RequestBody AuthRequest request) {
//         return new AuthResponse("dummy-token");
//     }
// }

package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // Normally you would validate email & password from DB here

        String token = jwtTokenProvider.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        // Normally you would save user to DB here

        String token = jwtTokenProvider.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
