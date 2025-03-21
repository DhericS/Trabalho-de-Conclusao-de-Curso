package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.acad.dto.AcademiaRequestDTO;
import com.example.NovoTesteCrud.domain.acad.dto.AcademiaResponseDTO;
import com.example.NovoTesteCrud.service.AcademiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/academias")
public class AcademiaController {

    @Autowired
    private AcademiaService academiaService;

    @GetMapping
    public ResponseEntity<List<AcademiaResponseDTO>> buscarTodasAcademias() {
        List<AcademiaResponseDTO> academias = academiaService.buscarTodasAcademias().stream()
                .map(AcademiaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(academias);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarAcademia(@RequestBody @Valid AcademiaRequestDTO data) {
        AcademiaResponseDTO academiaResponseDTO = new AcademiaResponseDTO(academiaService.registrarAcademia(data));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Academia cadastrada com sucesso!");
        response.put("academia", academiaResponseDTO);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarAcademia(@PathVariable Long id, @RequestBody @Valid AcademiaRequestDTO data) {
        AcademiaResponseDTO updatedAcademia = new AcademiaResponseDTO(academiaService.atualizarAcademia(data, id));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Academia atualizada com sucesso!");
        response.put("academia", updatedAcademia);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarAcademia(@PathVariable Long id) {
        academiaService.deletarAcademia(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Academia deletada com sucesso!");

        return ResponseEntity.ok(response);
    }

}
