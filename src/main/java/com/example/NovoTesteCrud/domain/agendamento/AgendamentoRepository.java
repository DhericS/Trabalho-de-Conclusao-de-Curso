package com.example.NovoTesteCrud.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> buscarPersonaleDataHora(Long personalId, LocalDateTime start, LocalDateTime end);

    List<Agendamento> buscarUsuarioPorId(Long userId);

    List<Agendamento> buscarUsuarioeDataHora(Long userId, LocalDateTime start, LocalDateTime end);

    List<Agendamento> buscarPersonaleUsuarioId(Long personalId, Long userId);

    List<Agendamento> buscarAcademiaePersonalId(Long academiaId, Long personalId);

    List<Agendamento> buscarPersonaleUsuarioeDataHora(Long personalId, Long userId, LocalDateTime start, LocalDateTime end);
}
