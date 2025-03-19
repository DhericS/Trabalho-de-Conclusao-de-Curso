package com.example.NovoTesteCrud.domain.acad;

import jakarta.validation.constraints.NotBlank;

public record RequestAcademia(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O endereço é obrigatório") String endereco,
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        Long id
) {}
