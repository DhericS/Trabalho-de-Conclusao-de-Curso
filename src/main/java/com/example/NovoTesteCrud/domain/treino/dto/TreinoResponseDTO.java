package com.example.NovoTesteCrud.domain.treino.dto;

import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioResponseDTO;
import com.example.NovoTesteCrud.domain.treino.Treino;
import java.util.List;

public record TreinoResponseDTO(
        Long id,
        String nome,
        String descricao,
        List<ExercicioResponseDTO> exercicios
) {
    public TreinoResponseDTO(Treino treino) {
        this(
                treino.getId(),
                treino.getNome(),
                treino.getDescricao(),
                treino.getExercicios().stream().map(ExercicioResponseDTO::new).toList()
        );
    }
}
