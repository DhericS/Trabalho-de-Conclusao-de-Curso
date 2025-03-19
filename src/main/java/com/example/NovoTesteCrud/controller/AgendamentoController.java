package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.dto.AgendamentoDTO;
import com.example.NovoTesteCrud.domain.agendamento.RequestAgendamento;
import com.example.NovoTesteCrud.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    //GET http://localhost:8080/user/7/date?start=2024-03-01T08:00:00&end=2024-03-10T18:00:00
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
    public ResponseEntity<Map<String, Object>> registerAgendamento(@RequestBody @Valid RequestAgendamento data) {
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamentoService.registerAgendamento(data));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Agendamento inserido com sucesso");
        response.put("agendamento", agendamentoDTO);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAgendamento(@RequestBody @Valid RequestAgendamento data, @PathVariable Long id) {
        AgendamentoDTO updatedAgendamento = new AgendamentoDTO(agendamentoService.updateAgendamento(data, id));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Agendamento atualizado com sucesso");
        response.put("agendamento", updatedAgendamento);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.deleteAgendamento(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Agendamento removido com sucesso");

        return ResponseEntity.ok(response);
    }

}
