package com.example.NovoTesteCrud.domain.agendamento;

import java.time.LocalDateTime;

public record RequestAgendamento(LocalDateTime dataHora,Long academiaId,Long personalId, Long userId) {

}
