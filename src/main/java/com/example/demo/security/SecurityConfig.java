// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationEntryPoint;
// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                         "/swagger-ui/**",
//                         "/v3/api-docs/**",
//                         "/auth/**"
//                 ).permitAll()
//                 .anyRequest().permitAll()
//             )
//             .exceptionHandling(ex ->
//                 ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//             );

//         return http.build();
//     }
// }






package com.example.demo.security;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // disable csrf for REST APIs
            .csrf(csrf -> csrf.disable())

            // stateless session for JWT
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // exception handling
            .exceptionHandling(ex ->
                ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )

            // authorization rules
            .authorizeHttpRequests(auth -> auth
                // public endpoints
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/auth/**"
                ).permitAll()

                // ADMIN only access
                .requestMatchers("/api/**").hasRole("ADMIN")

                // everything else blocked
                .anyRequest().authenticated()
            );

        // JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
