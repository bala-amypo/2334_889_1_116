package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
    .csrf(csrf -> csrf.disable())
    .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/auth/**").permitAll();
        auth.requestMatchers(HttpMethod.POST, "/api/contracts").hasRole("ADMIN");
        auth.anyRequest().authenticated();
    });

        // http
        //     .csrf(csrf -> csrf.disable())
        //     .authorizeHttpRequests(auth -> auth
        //         .requestMatchers(
        //                 "/swagger-ui/**",
        //                 "/v3/api-docs/**",
        //                 "/auth/**"
        //         ).permitAll()
        //         .requestMatchers(HttpMethod.POST, "/api/contracts").hasRole("ADMIN")
        //         .anyRequest().authenticated()
        //     )
        //     .exceptionHandling(ex ->
        //         ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        //     );

        // return http.build();
    }
}

