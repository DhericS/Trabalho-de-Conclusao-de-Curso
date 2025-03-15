package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.agendamento.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoDTO(Long id, LocalDateTime dataHora, String academiaNome, String personalNome, String userNome) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getAcademia().getNome(),
                agendamento.getPersonal().getName(),
                agendamento.getUser().getName()
        );
    }
}
