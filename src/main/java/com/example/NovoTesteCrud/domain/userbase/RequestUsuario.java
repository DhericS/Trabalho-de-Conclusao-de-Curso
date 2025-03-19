package com.example.NovoTesteCrud.domain.userbase;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestUsuario(
        @NotBlank(message = "O nome é obrigatório") String name,
        @NotBlank(message = "O e-mail é obrigatório") @Email(message = "E-mail inválido") String email,
        @NotBlank(message = "A senha é obrigatória") String senha,
        String telefone,
        @NotBlank(message = "O tipo de usuário é obrigatório") String tipoUsuario // NOVO CAMPO!
) {
}
