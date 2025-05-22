package com.example.NovoTesteCrud.domain.avaliacao.dto;

import com.example.NovoTesteCrud.domain.avaliacao.enums.TipoEntidade;
import jakarta.validation.constraints.*;

public record AvaliacaoRequestDTO(
        @Min(1) @Max(5) int nota,
        String comentario,
        @NotNull Long usuarioId,
        @NotNull TipoEntidade tipoEntidade,
        @NotNull Long entidadeId
) {}
