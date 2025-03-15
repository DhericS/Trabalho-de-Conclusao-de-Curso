package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.dto.AgendamentoDTO;
import com.example.NovoTesteCrud.domain.agendamento.RequestAgendamento;
import com.example.NovoTesteCrud.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos() {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAllAgendamentos()
                .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AgendamentoDTO>> getAgendamentosByUser(@PathVariable Long userId) {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAgendamentosByUser(userId)
                .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/user/{userId}/date")
    public ResponseEntity<List<AgendamentoDTO>> getAgendamentosByUserAndDate(
            @PathVariable Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAgendamentosByUserAndDate(userId, start, end)
                .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<AgendamentoDTO>> getAgendamentosByPersonal(@PathVariable Long personalId) {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAgendamentosByPersonal(personalId)
                .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/personal/{personalId}/user/{userId}")
    public ResponseEntity<List<AgendamentoDTO>> getAgendamentosByPersonalAndUser(
            @PathVariable Long personalId,
            @PathVariable Long userId) {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAgendamentosByPersonalAndUser(personalId, userId)
                .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/academia/{academiaId}/personal/{personalId}")
    public ResponseEntity<List<AgendamentoDTO>> getAgendamentosByAcademiaAndPersonal(
            @PathVariable Long academiaId,
            @PathVariable Long personalId) {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAgendamentosByAcademiaAndPersonal(academiaId, personalId)
                .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/personal/{personalId}/user/{userId}/date")
    public ResponseEntity<List<AgendamentoDTO>> getAgendamentosByPersonalUserAndDate(
            @PathVariable Long personalId,
            @PathVariable Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAgendamentosByPersonalUserAndDate(personalId, userId, start, end)
                .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping
    public ResponseEntity<Void> registerAgendamento(@RequestBody @Valid RequestAgendamento data) {
        agendamentoService.registerAgendamento(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(
            @RequestBody @Valid RequestAgendamento data,
            @PathVariable Long id) {
        AgendamentoDTO updatedAgendamento = new AgendamentoDTO(agendamentoService.updateAgendamento(data, id));
        return ResponseEntity.ok(updatedAgendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.deleteAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
