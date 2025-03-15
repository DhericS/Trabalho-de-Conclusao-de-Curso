package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.dto.PlanoAcademiaDTO;
import com.example.NovoTesteCrud.domain.planoacad.RequestPlanoAcademia;
import com.example.NovoTesteCrud.service.PlanoAcademiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/planos")
public class PlanoAcademiaController {

    @Autowired
    private PlanoAcademiaService planoAcademiaService;

    @GetMapping
    public ResponseEntity<List<PlanoAcademiaDTO>> getAllPlanos() {
        List<PlanoAcademiaDTO> planos = planoAcademiaService.getAllPlanos()
                .stream()
                .map(PlanoAcademiaDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(planos);
    }

    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<List<PlanoAcademiaDTO>> getPlanosByAcademia(@PathVariable Long academiaId) {
        List<PlanoAcademiaDTO> planos = planoAcademiaService.getPlanosByAcademia(academiaId)
                .stream()
                .map(PlanoAcademiaDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(planos);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerPlano(@RequestBody @Valid RequestPlanoAcademia data) {
        planoAcademiaService.registerPlano(data);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Plano de academia cadastrado com sucesso!");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updatePlano(@PathVariable Long id, @RequestBody @Valid RequestPlanoAcademia data) {
        planoAcademiaService.updatePlano(id, data);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Plano de academia atualizado com sucesso!");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePlano(@PathVariable Long id) {
        planoAcademiaService.deletePlano(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Plano de academia deletado com sucesso!");

        return ResponseEntity.ok(response);
    }

}
