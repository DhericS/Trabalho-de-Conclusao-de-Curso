package com.example.NovoTesteCrud.domain.atvd.dto;

import jakarta.validation.constraints.NotBlank;

public record AtividadeRequestDTO(
        @NotBlank(message = "O nome é obrigatorio") String nome,
        Long academiaId) {
}
