package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.dto.TreinoDTO;
import com.example.NovoTesteCrud.domain.treino.RequestTreino;
import com.example.NovoTesteCrud.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @GetMapping
    public ResponseEntity<List<TreinoDTO>> getAllTreinos() {
        return ResponseEntity.ok(treinoService.getAllTreinos());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerTreino(@RequestBody RequestTreino data) {
        TreinoDTO treinoDTO = treinoService.registerTreino(data);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Treino cadastrado com sucesso!");
        response.put("treino", treinoDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTreino(@PathVariable Long id, @RequestBody @Valid RequestTreino data) {
        TreinoDTO updatedTreino = treinoService.updateTreino(id, data);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Treino atualizado com sucesso!");
        response.put("treino", updatedTreino);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTreino(@PathVariable Long id) {
        treinoService.deleteTreino(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Treino deletado com sucesso!");

        return ResponseEntity.ok(response);
    }
}
