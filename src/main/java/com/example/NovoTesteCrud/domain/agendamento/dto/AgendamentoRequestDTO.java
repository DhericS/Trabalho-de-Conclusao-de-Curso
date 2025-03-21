package com.example.NovoTesteCrud.domain.agendamento.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        @NotNull(message = "A data e hora do agendamento são obrigatórias")
        @Future(message = "A data do agendamento deve ser no futuro")
        LocalDateTime dataHora,

        @NotNull(message = "A academia do agendamento é obrigatória")
        Long academiaId,

        @NotNull(message = "O personal do agendamento é obrigatório")
        Long personalId,

        @NotNull(message = "O usuário do agendamento é obrigatório")
        Long userId
) {
}
