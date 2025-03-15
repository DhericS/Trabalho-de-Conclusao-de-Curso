package com.example.NovoTesteCrud.domain.useracadadmin;

public record RequestUserAcadAdmin(
        String name,
        String email,
        String password,
        String cnpj,
        String telefone,
        Long academiaId,
        String academiaNome,
        String academiaEndereco,
        String academiaTelefone
) {}
