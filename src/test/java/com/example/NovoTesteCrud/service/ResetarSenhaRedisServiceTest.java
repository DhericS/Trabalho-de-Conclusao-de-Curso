package com.example.NovoTesteCrud.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ResetarSenhaRedisServiceTest {

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOps;

    @InjectMocks
    private ResetarSenhaRedisService resetarSenhaRedisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOps);
    }

    @Test
    void deveSalvarTokenNoRedis() {
        String token = "testando123";
        String email = "teste@gmail.com";

        resetarSenhaRedisService.salvarToken(token, email, 10);

        verify(valueOps).set(token, email, 10, TimeUnit.MINUTES);
    }

    @Test
    void deveBuscarEmailPorToken() {
        String token = "testando123";
        when(valueOps.get(token)).thenReturn("teste@gmail.com");

        String email = resetarSenhaRedisService.buscarEmailPorToken(token);

        assertThat(email).isEqualTo("teste@gmail.com");
    }

    @Test
    void deveDeletarTokenDoRedis() {
        String token = "testando123";

        resetarSenhaRedisService.deletarToken(token);

        verify(redisTemplate).delete(token);
    }
}