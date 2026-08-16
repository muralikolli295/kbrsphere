package com.kbrsphere.property_management.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    private final String SECRET = "kbrsphere-secret-key-user-management";

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token).getBody();
    }

    public Long extractUserId(String token) {
        Object userId = extractAllClaims(token).get("userId");
        return Long.valueOf(userId.toString());
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}