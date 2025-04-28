package com.example.NovoTesteCrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ResetarSenhaRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void salvarToken(String token, String email, long minutosExpiracao) {
        redisTemplate.opsForValue().set(token, email, minutosExpiracao, TimeUnit.MINUTES);
    }

    public String buscarEmailPorToken(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    public void deletarToken(String token) {
        redisTemplate.delete(token);
    }
}
