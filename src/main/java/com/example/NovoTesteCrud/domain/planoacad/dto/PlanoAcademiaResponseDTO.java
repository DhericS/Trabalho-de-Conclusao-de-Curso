package com.example.NovoTesteCrud.domain.planoacad.dto;

import com.example.NovoTesteCrud.domain.planoacad.PlanoAcademia;

public record PlanoAcademiaResponseDTO(Long id, String nome, String descricao, Double preco, Long academiaId, String academiaNome) {
    public PlanoAcademiaResponseDTO(PlanoAcademia plano) {
        this(
                plano.getId(),
                plano.getNome(),
                plano.getDescricao(),
                plano.getPreco(),
                plano.getAcademia().getId(),
                plano.getAcademia().getNome()
        );
    }
}
