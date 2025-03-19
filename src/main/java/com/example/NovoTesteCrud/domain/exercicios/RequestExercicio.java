package com.example.NovoTesteCrud.domain.exercicios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestExercicio(
        @NotBlank(message = "O nome do exercício é obrigatório") String nome,
        @NotNull(message = "O número de séries é obrigatório") Integer series,
        @NotNull(message = "O número de repetições é obrigatório") Integer repeticoes,
        @NotNull(message = "O grupo muscular é obrigatório") GrupoMuscular grupoMuscular
) {
}
