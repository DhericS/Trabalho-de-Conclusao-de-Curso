package com.example.NovoTesteCrud.domain.avaliacao.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequestDTO(
        @NotNull(message = "O ID do usuário é obrigatório") Long userId,
        @NotBlank(message = "A descrição é obrigatória") String descricao,
        @NotNull(message = "A quantidade de estrelas é obrigatória")
        @Min(value = 1, message = "A avaliação mínima é 1 estrela")
        @Max(value = 5, message = "A avaliação máxima é 5 estrelas")
        Integer estrelas,
        Long academiaId,
        Long personalId
) {}
