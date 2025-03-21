package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.agendamento.dto.AgendamentoResponseDTO;
import com.example.NovoTesteCrud.domain.agendamento.dto.AgendamentoRequestDTO;
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
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarTodosAgendamentos() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.buscarTodosAgendamentos()
                .stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentosPorUsuario(@PathVariable Long userId) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.buscarAgendamentosPorUsuario(userId)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    //GET http://localhost:8080/user/7/date?start=2024-03-01T08:00:00&end=2024-03-10T18:00:00
    @GetMapping("/user/{userId}/date")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentosPorUsuarioeData(
            @PathVariable Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.buscarAgendamentosPorUsuarioeData(userId, start, end)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentosPorPersonal(@PathVariable Long personalId) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.buscarAgendamentosPorPersonal(personalId)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/personal/{personalId}/user/{userId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentosPorPersonaleUsuario(
            @PathVariable Long personalId,
            @PathVariable Long userId) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.buscarAgendamentosPorPersonaleUsuario(personalId, userId)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/academia/{academiaId}/personal/{personalId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentosPorAcademiaePersonal(
            @PathVariable Long academiaId,
            @PathVariable Long personalId) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.buscarAgendamentosPorAcademiaePersonal(academiaId, personalId)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/personal/{personalId}/user/{userId}/date")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentosPorPersonaleUsuarioeData(
            @PathVariable Long personalId,
            @PathVariable Long userId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.buscarAgendamentosPorPersonaleUsuarioeData(personalId, userId, start, end)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarAgentamento(@RequestBody @Valid AgendamentoRequestDTO data) {
        AgendamentoResponseDTO agendamentoResponseDTO = new AgendamentoResponseDTO(agendamentoService.registrarAgentamento(data));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Agendamento inserido com sucesso");
        response.put("agendamento", agendamentoResponseDTO);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarAgentamento(@RequestBody @Valid AgendamentoRequestDTO data, @PathVariable Long id) {
        AgendamentoResponseDTO updatedAgendamento = new AgendamentoResponseDTO(agendamentoService.atualizarAgentamento(data, id));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Agendamento atualizado com sucesso");
        response.put("agendamento", updatedAgendamento);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarAgentamento(@PathVariable Long id) {
        agendamentoService.deletarAgentamento(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Agendamento removido com sucesso");

        return ResponseEntity.ok(response);
    }

}
