package com.example.NovoTesteCrud.config.security;

import com.example.NovoTesteCrud.domain.userbase.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    private final String secret = "testestestestestestestestesteste64testestestestestestest2HS512ok123456789";
    private final Long expiration = 1000L * 60 * 60; // 1 hora

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();

        try {
            var secretField = JwtUtil.class.getDeclaredField("secret");
            secretField.setAccessible(true);
            secretField.set(jwtUtil, secret);

            var expirationField = JwtUtil.class.getDeclaredField("expiration");
            expirationField.setAccessible(true);
            expirationField.set(jwtUtil, expiration);
        } catch (Exception e) {
            fail("Erro ao configurar JwtUtil");
        }
    }

    @Test
    void deveGerarTokenValidoEExtrairInformacoes() {

        String email = "teste@gmail.com";
        Role role = Role.USERACAD;

        String token = jwtUtil.generateToken(email, role);

        assertNotNull(token);
        assertTrue(jwtUtil.isTokenValid(token));
        assertEquals(email, jwtUtil.extractEmail(token));
        assertEquals(role, jwtUtil.extractRole(token));
    }

    @Test
    void deveDetectarTokenInvalidoComAssinaturaErrada() {

        String tokenInvalido = "TestandoTokenInvalido";

        assertFalse(jwtUtil.isTokenValid(tokenInvalido));
    }
}