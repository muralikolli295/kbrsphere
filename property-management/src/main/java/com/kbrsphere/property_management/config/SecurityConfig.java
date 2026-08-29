package com.kbrsphere.property_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public APIs
                        .requestMatchers(HttpMethod.GET, "/properties", "/properties/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/properties/my-properties").authenticated()
                        .requestMatchers(HttpMethod.GET, "/properties/*").permitAll()
                        // Authenticated APIs
                        .requestMatchers(HttpMethod.POST, "/properties").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/properties/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/properties/*").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/properties/*/status").authenticated()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}