package com.example.NovoTesteCrud.domain.avaliacao.dto;

import com.example.NovoTesteCrud.domain.avaliacao.Avaliacao;

import java.time.LocalDateTime;

public record AvaliacaoResponseDTO(
        Long id,
        int nota,
        String comentario,
        Long usuarioId,
        String tipoEntidade,
        Long entidadeId,
        LocalDateTime dataCriacao
) {
    public AvaliacaoResponseDTO(Avaliacao a) {
        this(
                a.getId(),
                a.getNota(),
                a.getComentario(),
                a.getUsuarioId(),
                a.getTipoEntidade().name(),
                a.getEntidadeId(),
                a.getDataCriacao()
        );
    }
}
