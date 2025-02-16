package com.example.chatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/chat/**").permitAll() // Allow WebSocket connections
                        .requestMatchers("/ws/**").permitAll()   // Ensure WebSocket endpoints are accessible
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF (needed for WebSockets)
                .headers(AbstractHttpConfigurer::disable) // 🔹 Disable security headers properly (fixes frameOptions issue)
                .formLogin(AbstractHttpConfigurer::disable) // Disable default login
                .httpBasic(AbstractHttpConfigurer::disable) // Disable basic auth
                .build();
    }
}


