package com.example.NovoTesteCrud.domain.exercicios.dto;

import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.treino.Treino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExercicioRequestDTO(
        @NotBlank(message = "O nome do exercício é obrigatório") String nome,
        @NotNull(message = "O número de séries é obrigatório") Integer series,
        @NotNull(message = "O número de repetições é obrigatório") Integer repeticoes,
        @NotNull(message = "O grupo muscular é obrigatório") GrupoMuscular grupoMuscular,
        @NotNull(message = "O ID do treino é obrigatório") Long treinoId
) {
}
