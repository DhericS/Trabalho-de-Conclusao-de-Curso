package com.example.NovoTesteCrud.domain.exercicios.dto;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;

public record ExercicioResponseDTO(
        Long id,
        String nome,
        Integer series,
        Integer repeticoes,
        GrupoMuscular grupoMuscular,
        String imagemUrl
) {
    public ExercicioResponseDTO(Exercicio exercicio) {
        this(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getSeries(),
                exercicio.getRepeticoes(),
                exercicio.getGrupoMuscular(),
                exercicio.getImagemUrl()
        );
    }
}
