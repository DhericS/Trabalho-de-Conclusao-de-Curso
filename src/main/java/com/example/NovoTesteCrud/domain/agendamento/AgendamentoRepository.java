package com.example.NovoTesteCrud.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPersonalIdAndDataHoraBetween(Long personalId, LocalDateTime start, LocalDateTime end);

    // Buscar por usuário
    List<Agendamento> findByUserId(Long userId);

    // Buscar por usuário e intervalo de datas
    List<Agendamento> findByUserIdAndDataHoraBetween(Long userId, LocalDateTime start, LocalDateTime end);

    // Buscar por personal e usuário
    List<Agendamento> findByPersonalIdAndUserId(Long personalId, Long userId);

    // Buscar por academia e personal
    List<Agendamento> findByAcademiaIdAndPersonalId(Long academiaId, Long personalId);

    // Buscar por personal, usuário e intervalo de datas
    List<Agendamento> findByPersonalIdAndUserIdAndDataHoraBetween(Long personalId, Long userId, LocalDateTime start, LocalDateTime end);
}
