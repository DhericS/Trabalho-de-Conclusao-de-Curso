package com.example.NovoTesteCrud.config.security;

import com.example.NovoTesteCrud.domain.userbase.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Metodo para gerar o token JWT
    public String generateToken(String email, Role role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Metodo para extrair o email do token JWT
    public String extractEmail(String token) {
        return parseToken(token).getBody().getSubject();
    }

    // Metodo para extrair a role do token JWT
    public Role extractRole(String token) {
        String roleString = (String) parseToken(token).getBody().get("role");
        try {
            return Role.valueOf(roleString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Role inválida no token: " + roleString);
            throw e; 
        }
    }

    // Metodo para verificar se o token JWT é válido
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Metodo para verificar se o token JWT expirou
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}
