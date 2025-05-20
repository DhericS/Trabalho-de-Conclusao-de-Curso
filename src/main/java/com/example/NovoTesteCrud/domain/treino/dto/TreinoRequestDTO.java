package com.example.NovoTesteCrud.domain.treino.dto;

import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TreinoRequestDTO(
        @NotBlank(message = "O nome do treino é obrigatório") String nome,
        @NotBlank(message = "A descrição do treino é obrigatória") String descricao,
        Long userId,
        Long personalId,
        List<ExercicioRequestDTO> exercicios
) {
}
