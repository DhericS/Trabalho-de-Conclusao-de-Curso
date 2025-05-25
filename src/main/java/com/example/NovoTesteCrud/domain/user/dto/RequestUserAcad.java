package com.example.NovoTesteCrud.domain.user.dto;

import com.example.NovoTesteCrud.domain.userbase.Role;
import com.example.NovoTesteCrud.domain.userbase.dto.IRequestUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestUserAcad(
        Long id,
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O e-mail é obrigatório") @Email(message = "E-mail inválido") String email,
        String senha,
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        String imagemUrl,
        Role role
) implements IRequestUsuario {
    @Override
    public String tipoUsuario() {
        return "useracad";
    }

}
