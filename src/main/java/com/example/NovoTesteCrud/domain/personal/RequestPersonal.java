package com.example.NovoTesteCrud.domain.personal;

import com.example.NovoTesteCrud.domain.userbase.IRequestUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestPersonal(
        @NotBlank(message = "O nome é obrigatório") String name,
        @NotBlank(message = "O e-mail é obrigatório") @Email(message = "E-mail inválido") String email,
        @NotBlank(message = "A senha é obrigatória") String senha,
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        @NotBlank(message = "O CREF é obrigatório") String cref
) implements IRequestUsuario {
    @Override
    public String tipoUsuario() {
        return "personal";
    }
}