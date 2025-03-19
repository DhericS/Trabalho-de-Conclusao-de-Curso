package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.atvd.Atividade;
import com.example.NovoTesteCrud.domain.atvd.RequestAtividade;
import com.example.NovoTesteCrud.dto.AtividadeDTO;
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
    public ResponseEntity<List<AtividadeDTO>> getAllAtividades() {
        List<AtividadeDTO> atividades = atividadeService.getAllAtividades()
                .stream().map(AtividadeDTO::new).toList();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<List<AtividadeDTO>> getAtividadesByAcademia(@PathVariable Long academiaId) {
        List<AtividadeDTO> atividades = atividadeService.getAtividadesByAcademia(academiaId)
                .stream().map(AtividadeDTO::new).toList();
        return ResponseEntity.ok(atividades);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerAtividade(@RequestBody @Valid RequestAtividade data) {
        AtividadeDTO atividadeDTO = new AtividadeDTO(atividadeService.registerAtividade(data));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Atividade cadastrada com sucesso!");
        response.put("atividade", atividadeDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAtividade(@PathVariable Long id, @RequestBody @Valid RequestAtividade data) {
        AtividadeDTO updatedAtividade = new AtividadeDTO(atividadeService.updateAtividade(data, id));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Atividade atualizada com sucesso!");
        response.put("atividade", updatedAtividade);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAtividade(@PathVariable Long id) {
        atividadeService.deleteAtividade(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Atividade removida com sucesso");
        return ResponseEntity.ok(response);
    }
}
