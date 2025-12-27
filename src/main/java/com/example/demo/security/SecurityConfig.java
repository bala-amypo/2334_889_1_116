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
