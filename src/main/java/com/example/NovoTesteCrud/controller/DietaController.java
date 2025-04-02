package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.dieta.dto.DietaRequestDTO;
import com.example.NovoTesteCrud.domain.dieta.dto.DietaResponseDTO;
import com.example.NovoTesteCrud.service.DietaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dietas")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @GetMapping
    public ResponseEntity<List<DietaResponseDTO>> listar() {
        var lista = dietaService.listarTodasDieta().stream().map(DietaResponseDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<DietaResponseDTO> criarDieta(@RequestBody @Valid DietaRequestDTO dto) {
        var dieta = dietaService.criarDieta(dto);
        return ResponseEntity.ok(new DietaResponseDTO(dieta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietaResponseDTO> atualizarDieta(@PathVariable Long id, @RequestBody @Valid DietaRequestDTO dto) {
        var dieta = dietaService.atualizarDieta(id, dto);
        return ResponseEntity.ok(new DietaResponseDTO(dieta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDieta(@PathVariable Long id) {
        dietaService.deletarDieta(id);
        return ResponseEntity.noContent().build();
    }
}
