package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.agendamento.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPersonalIdAndDataHoraBetween(Long personalId, LocalDateTime start, LocalDateTime end);

    List<Agendamento> findByUserId(Long userId);

    List<Agendamento> findByUserIdAndDataHoraBetween(Long userId, LocalDateTime start, LocalDateTime end);

    List<Agendamento> findByPersonalIdAndUserId(Long personalId, Long userId);

    List<Agendamento> findByPersonalId(Long personalId);

    List<Agendamento> findByAcademiaIdAndPersonalId(Long academiaId, Long personalId);

    List<Agendamento> findByPersonalIdAndUserIdAndDataHoraBetween(Long personalId, Long userId, LocalDateTime start, LocalDateTime end);

}
