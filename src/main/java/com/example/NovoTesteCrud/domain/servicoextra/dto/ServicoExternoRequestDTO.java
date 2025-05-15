package com.example.NovoTesteCrud.domain.servicoextra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServicoExternoRequestDTO(
        @NotBlank(message = "O nome do serviço é obrigatório")
        String nome,

        @NotBlank(message = "O tipo do serviço é obrigatório")
        String tipo,

        @NotBlank(message = "A descrição do serviço é obrigatória")
        String descricao,

        @NotBlank(message = "O contato do serviço é obrigatório")
        String contato,

        String link,

        @NotNull(message = "O ID da academia é obrigatório")
        Long academiaId
) {}
