package com.example.NovoTesteCrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ResetarSenhaRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // Metodo para salvar o token de redefinição de senha com o email associado
    public void salvarToken(String token, String email, long minutosExpiracao) {
        redisTemplate.opsForValue().set(token, email, minutosExpiracao, TimeUnit.MINUTES);
    }

    // Metodo para buscar o email associado ao token de redefinição de senha
    public String buscarEmailPorToken(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    // Metodo para deletar o token de redefinição de senha
    public void deletarToken(String token) {
        redisTemplate.delete(token);
    }
}
