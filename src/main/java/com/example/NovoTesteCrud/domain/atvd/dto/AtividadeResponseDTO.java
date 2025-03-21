package com.example.NovoTesteCrud.domain.atvd.dto;

import com.example.NovoTesteCrud.domain.atvd.Atividade;

public record AtividadeResponseDTO(Long id, String nome) {
    public AtividadeResponseDTO(Atividade atividade) {
        this(atividade.getId(), atividade.getNome());
    }
}
