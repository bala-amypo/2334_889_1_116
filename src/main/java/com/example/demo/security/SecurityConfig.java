// package com.example.demo.config;

// import com.example.demo.security.CustomUserDetailsService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @RequiredArgsConstructor
// public class SecurityConfig {

//     private final CustomUserDetailsService userDetailsService;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//         return http.getSharedObject(AuthenticationManagerBuilder.class)
//                 .userDetailsService(userDetailsService)
//                 .passwordEncoder(passwordEncoder())
//                 .and()
//                 .build();
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .authorizeHttpRequests()
//             .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//             .anyRequest().authenticated()
//             .and()
//             .httpBasic();
//         return http.build();
//     }
// }
