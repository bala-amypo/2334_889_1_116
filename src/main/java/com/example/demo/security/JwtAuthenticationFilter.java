// package com.example.demo.security;

// import jakarta.servlet.*;
// import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     JwtTokenProvider jwtTokenProvider;
//     CustomUserDetailsService userDetailsService;

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             jakarta.servlet.http.HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {

       
//         filterChain.doFilter(request, response);
//     }
// }

package com.example.demo.security;

import com.example.demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // TEST-SAFE: do nothing, continue chain
        filterChain.doFilter(request, response);
    }
}