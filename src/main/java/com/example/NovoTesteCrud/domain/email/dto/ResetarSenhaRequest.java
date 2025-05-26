package com.example.NovoTesteCrud.domain.email.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetarSenhaRequest {
    private String token;
    private String novaSenha;
}
