package com.example.NovoTesteCrud.domain.email;

import lombok.Data;

@Data
public class ResetarSenhaToken {
    private String token;
    private String userEmail;

    public ResetarSenhaToken() {
    }

    public ResetarSenhaToken(String token, String userEmail) {
        this.token = token;
        this.userEmail = userEmail;
    }
}
