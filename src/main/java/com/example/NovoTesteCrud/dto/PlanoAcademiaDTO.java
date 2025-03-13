package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.planoacad.PlanoAcademia;

public record PlanoAcademiaDTO(Long id, String nome, String descricao, Double preco, String academiaNome) {
    public PlanoAcademiaDTO(PlanoAcademia plano) {
        this(plano.getId(), plano.getNome(), plano.getDescricao(), plano.getPreco(), plano.getAcademia().getNome());
    }
}
