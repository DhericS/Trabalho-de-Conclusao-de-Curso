package com.example.NovoTesteCrud.domain.servicoextra.dto;

import com.example.NovoTesteCrud.domain.servicoextra.ServicoExterno;

public record ServicoExternoResponseDTO(
        Long id,
        String nome,
        String tipo,
        String descricao,
        String contato,
        String link,
        String academiaNome
) {
    public ServicoExternoResponseDTO(ServicoExterno servico) {
        this(
                servico.getId(),
                servico.getNome(),
                servico.getTipo(),
                servico.getDescricao(),
                servico.getContato(),
                servico.getLink(),
                servico.getAcademia().getNome()
        );
    }
}
