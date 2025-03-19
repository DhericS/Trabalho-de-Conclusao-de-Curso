package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.atvd.Atividade;

public record AtividadeDTO(Long id, String nome) {
    public AtividadeDTO(Atividade atividade) {
        this(atividade.getId(), atividade.getNome());
    }
}
