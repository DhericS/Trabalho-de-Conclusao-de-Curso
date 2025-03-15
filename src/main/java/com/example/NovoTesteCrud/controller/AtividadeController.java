package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.atvd.Atividade;
import com.example.NovoTesteCrud.domain.atvd.RequestAtividade;
import com.example.NovoTesteCrud.service.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<Atividade>> getAllAtividades() {
        return ResponseEntity.ok(atividadeService.getAllAtividades());
    }

    @PostMapping
    public ResponseEntity<Void> registerAtividade(@RequestBody @Valid RequestAtividade data) {
        atividadeService.registerAtividade(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Atividade> updateAtividade(@RequestBody @Valid RequestAtividade data, @RequestParam Long id) {
        return ResponseEntity.ok(atividadeService.updateAtividade(data, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Long id) {
        atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }
}
