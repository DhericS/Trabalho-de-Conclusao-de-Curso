package com.example.NovoTesteCrud.domain.atvd.dto;

import com.example.NovoTesteCrud.domain.atvd.Atividade;
import com.example.NovoTesteCrud.domain.atvd.DiaSemana;

public record AtividadeResponseDTO(
        Long id,
        String nome,
        DiaSemana diaSemana,
        String horario,
        Long academiaId
) {
    public AtividadeResponseDTO(Atividade a) {
        this(
                a.getId(),
                a.getNome(),
                a.getDiaSemana(),
                a.getHorario(),
                a.getAcademia() != null ? a.getAcademia().getId() : null
        );
    }
}
