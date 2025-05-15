package com.example.NovoTesteCrud.domain.exercicios.dto;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;

public record ExercicioSimplesDTO(
        Long id,
        String nome,
        Integer series,
        Integer repeticoes,
        GrupoMuscular grupoMuscular
) {
    public ExercicioSimplesDTO(Exercicio exercicio) {
        this(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getSeries(),
                exercicio.getRepeticoes(),
                exercicio.getGrupoMuscular()
        );
    }
}
