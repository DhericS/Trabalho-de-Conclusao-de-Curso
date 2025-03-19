package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;

public record ExercicioDTO(
        Long id,
        String nome,
        Integer series,
        Integer repeticoes,
        GrupoMuscular grupoMuscular
) {
    public ExercicioDTO(Exercicio exercicio) {
        this(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getSeries(),
                exercicio.getRepeticoes(),
                exercicio.getGrupoMuscular()
        );
    }
}
