package com.example.NovoTesteCrud.domain.useracadadmin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestUserAcadAdmin(
        @NotBlank(message = "O nome é obrigatório") String name,
        @Email(message = "E-mail inválido") @NotBlank(message = "O e-mail é obrigatório") String email,
        @NotBlank(message = "O CNPJ é obrigatório") String cnpj,
        @NotBlank(message = "A senha é obrigatória") String password,
        Long academiaId,
        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter entre 10 e 11 dígitos") String telefone
) {}
