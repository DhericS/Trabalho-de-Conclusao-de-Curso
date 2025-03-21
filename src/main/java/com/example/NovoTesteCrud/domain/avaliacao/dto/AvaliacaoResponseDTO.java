package com.example.NovoTesteCrud.domain.avaliacao.dto;

import com.example.NovoTesteCrud.domain.avaliacao.Avaliacao;

public record AvaliacaoResponseDTO(Long id, String descricao, Integer estrelas, String userNome, String academiaNome, String personalNome) {
    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this(
                avaliacao.getId(),
                avaliacao.getDescricao(),
                avaliacao.getEstrelas(),
                avaliacao.getUser().getNome(),
                avaliacao.getAcademia() != null ? avaliacao.getAcademia().getNome() : null,
                avaliacao.getPersonal() != null ? avaliacao.getPersonal().getNome() : null
        );
    }
}
