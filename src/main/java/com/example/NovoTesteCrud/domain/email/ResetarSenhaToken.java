package com.example.NovoTesteCrud.domain.email;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class ResetarSenhaToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private LocalDateTime expirationDate;

    private String userEmail;

    public ResetarSenhaToken() {
    }

    public ResetarSenhaToken(String userEmail) {
        this.token = UUID.randomUUID().toString();
        this.expirationDate = LocalDateTime.now().plusHours(1);
        this.userEmail = userEmail;
    }

}