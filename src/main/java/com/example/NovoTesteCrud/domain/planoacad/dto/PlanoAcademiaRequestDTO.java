package com.example.NovoTesteCrud.domain.planoacad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanoAcademiaRequestDTO(
        @NotBlank(message = "O nome do plano é obrigatório")
        String nome,

        @NotBlank(message = "A descrição do plano é obrigatória")
        String descricao,

        @NotNull(message = "O preço do plano é obrigatório")
        Double preco,

        @NotNull(message = "O ID da academia é obrigatório")
        Long academiaId
) {
}
