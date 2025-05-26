package com.example.NovoTesteCrud.domain.useradmin.dto;

import com.example.NovoTesteCrud.domain.userbase.Role;
import com.example.NovoTesteCrud.domain.userbase.dto.IRequestUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestUserAdmin(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,


        String senha,

        @NotBlank(message = "O numero de telefone é obrigatório")
        String telefone,
        Long id,
        Role role
) implements IRequestUsuario {
        @Override
        public String tipoUsuario() {
                return "useradmin";
        }

}