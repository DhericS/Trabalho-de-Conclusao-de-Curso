package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.treino.Treino;
import java.util.List;

public record TreinoDTO(
        Long id,
        String nome,
        String descricao,
        List<ExercicioDTO> exercicios
) {
    public TreinoDTO(Treino treino) {
        this(
                treino.getId(),
                treino.getNome(),
                treino.getDescricao(),
                treino.getExercicios().stream().map(ExercicioDTO::new).toList()
        );
    }
}
