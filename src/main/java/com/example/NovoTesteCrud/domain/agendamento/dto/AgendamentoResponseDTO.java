package com.example.NovoTesteCrud.domain.agendamento.dto;

import com.example.NovoTesteCrud.domain.agendamento.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(Long id, LocalDateTime dataHora, String academiaNome, String personalNome, String userNome) {
    public AgendamentoResponseDTO(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getAcademia().getNome(),
                agendamento.getPersonal().getNome(),
                agendamento.getUser().getNome()
        );
    }
}
