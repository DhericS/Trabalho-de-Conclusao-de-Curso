package com.example.NovoTesteCrud.domain.personal.dto;

import com.example.NovoTesteCrud.domain.userbase.Role;
import com.example.NovoTesteCrud.domain.userbase.dto.IRequestUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestPersonal(
        Long id,
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O e-mail é obrigatório") @Email(message = "E-mail inválido") String email,
        String senha,
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        @NotBlank(message = "O CREF é obrigatório") String cref,
        String imagemUrl,
        Role role
) implements IRequestUsuario {
    @Override
    public String tipoUsuario() {
        return "personal";
    }
}