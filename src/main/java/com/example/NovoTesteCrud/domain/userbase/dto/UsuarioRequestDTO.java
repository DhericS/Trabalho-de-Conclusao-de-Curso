package com.example.NovoTesteCrud.domain.userbase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório") String name,
        @NotBlank(message = "O e-mail é obrigatório") @Email(message = "E-mail inválido") String email,
        @NotBlank(message = "A senha é obrigatória") String senha,
        @NotBlank(message = "O telefone é obrigatório") String telefone
) implements IRequestUsuario {

    @Override
    public String tipoUsuario() {
        return "user";
    }
}
