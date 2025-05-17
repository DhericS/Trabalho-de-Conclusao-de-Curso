package com.example.NovoTesteCrud.domain.useracadadmin;

import com.example.NovoTesteCrud.domain.userbase.Role;
import com.example.NovoTesteCrud.domain.userbase.dto.IRequestUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestUserAcadAdmin(
        @NotBlank(message = "O nome é obrigatório") String name,
        @Email(message = "E-mail inválido") @NotBlank(message = "O e-mail é obrigatório") String email,
        @NotBlank(message = "O CNPJ é obrigatório") String cnpj,
        String senha,
        Long academiaId,
        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter entre 10 e 11 dígitos") String telefone,
        Long id,
        Role role
) implements IRequestUsuario {
        @Override
        public String tipoUsuario() {
                return "useracadadmin";
        }

}