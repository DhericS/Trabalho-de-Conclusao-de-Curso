package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.atvd.dto.AtividadeRequestDTO;
import com.example.NovoTesteCrud.domain.atvd.dto.AtividadeResponseDTO;
import com.example.NovoTesteCrud.service.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeResponseDTO>> buscarTodasAtividades() {
        List<AtividadeResponseDTO> atividades = atividadeService.buscarTodasAtividades()
                .stream().map(AtividadeResponseDTO::new).toList();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<List<AtividadeResponseDTO>> buscarTodasAtividadesPorAcademia(@PathVariable Long academiaId) {
        List<AtividadeResponseDTO> atividades = atividadeService.buscarTodasAtividadesPorAcademia(academiaId)
                .stream().map(AtividadeResponseDTO::new).toList();
        return ResponseEntity.ok(atividades);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarAtividade(@RequestBody @Valid AtividadeRequestDTO data) {
        AtividadeResponseDTO atividadeResponseDTO = new AtividadeResponseDTO(atividadeService.registrarAtividade(data));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Atividade cadastrada com sucesso!");
        response.put("atividade", atividadeResponseDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarAtividade(@PathVariable Long id, @RequestBody @Valid AtividadeRequestDTO data) {
        AtividadeResponseDTO updatedAtividade = new AtividadeResponseDTO(atividadeService.atualizarAtividade(data, id));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Atividade atualizada com sucesso!");
        response.put("atividade", updatedAtividade);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarAtividade(@PathVariable Long id) {
        atividadeService.deletarAtividade(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Atividade removida com sucesso");
        return ResponseEntity.ok(response);
    }
}
