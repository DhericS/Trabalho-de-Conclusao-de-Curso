package com.example.NovoTesteCrud.domain.atvd.dto;

import com.example.NovoTesteCrud.domain.atvd.DiaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtividadeRequestDTO(
        @NotBlank(message = "O nome é obrigatorio") String nome,
        @NotNull(message = "O dia da semana é obrigatório") DiaSemana diaSemana,
        @NotBlank(message = "O horário é obrigatório") String horario,
        @NotNull(message = "O ID da academia é obrigatório") Long academiaId) {
}
