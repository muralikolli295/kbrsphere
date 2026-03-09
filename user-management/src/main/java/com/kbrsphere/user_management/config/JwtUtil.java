package com.kbrsphere.user_management.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Configuration
public class JwtUtil {

    private final String SECRET = "kbrsphere-secret-key-user-management";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuer("kbrsphere")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 hr
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}