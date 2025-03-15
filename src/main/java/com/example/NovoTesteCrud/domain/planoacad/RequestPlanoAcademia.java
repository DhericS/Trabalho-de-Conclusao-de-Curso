package com.example.NovoTesteCrud.domain.planoacad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPlanoAcademia(
        String nome,
        String descricao,
        Double preco,
        Long academiaId
) {}
