package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.email.ResetarSenhaToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetarSenhaRepository extends JpaRepository<ResetarSenhaToken, Long> {
    Optional<ResetarSenhaToken> findByToken(String token);
}