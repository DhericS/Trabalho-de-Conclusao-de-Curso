package com.example.NovoTesteCrud.domain.treino;

import com.example.NovoTesteCrud.domain.exercicios.RequestExercicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record RequestTreino(
        @NotBlank(message = "O nome do treino é obrigatório") String nome,
        @NotBlank(message = "A descrição do treino é obrigatória") String descricao,
        @NotNull(message = "O usuário é obrigatório") Long userId,
        List<RequestExercicio> exercicios
) {
}
