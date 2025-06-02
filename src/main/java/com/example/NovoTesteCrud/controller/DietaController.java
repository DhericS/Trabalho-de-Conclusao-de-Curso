package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.dieta.dto.DietaRequestDTO;
import com.example.NovoTesteCrud.domain.dieta.dto.DietaResponseDTO;
import com.example.NovoTesteCrud.domain.dieta.dto.DietaFilterDto;
import com.example.NovoTesteCrud.domain.dieta.enums.TipoDieta;
import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.service.DietaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dieta")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    // Endpoint para listar todas as dietas ou filtrar por personalId
    @GetMapping
    public ResponseEntity<List<DietaResponseDTO>> listar(@RequestParam(required = false) Long personalId) {
        if (personalId != null) {
            return ResponseEntity.ok(dietaService.listarPorPersonal(personalId));
        }
        return ResponseEntity.ok(dietaService.listarTodasDieta());
    }

    // Endpoint para buscar dietas filtradas com busca por nome e tipos
    @GetMapping("/filtro")
    public List<DietaResponseDTO> buscarFiltradas(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> tipos
    ) {
        List<TipoDieta> tiposEnum = null;

        if (tipos != null && !tipos.isEmpty()) {
            tiposEnum = tipos.stream()
                    .map(String::toUpperCase)
                    .map(TipoDieta::valueOf)
                    .toList();
        }

        DietaFilterDto filtro = new DietaFilterDto(tiposEnum);
        return dietaService.buscarDietasFiltradasComBusca(search, filtro);
    }

    // Endpoint para buscar dieta por ID
    @GetMapping("/{id}")
    public ResponseEntity<DietaResponseDTO> buscarPorId(@PathVariable Long id) {
        Dieta dieta = dietaService.buscarDietaPorId(id);
        if (dieta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DietaResponseDTO(dieta));
    }


    // Endpoints para criar nova dieta
    @PostMapping
    public ResponseEntity<DietaResponseDTO> criarDieta(@RequestBody @Valid DietaRequestDTO dto) {
        var dieta = dietaService.criarDieta(dto);
        return ResponseEntity.ok(new DietaResponseDTO(dieta));
    }

    // Endpoints para atualizar dieta existente
    @PutMapping("/{id}")
    public ResponseEntity<DietaResponseDTO> atualizarDieta(@PathVariable Long id, @RequestBody @Valid DietaRequestDTO dto) {
        var dieta = dietaService.atualizarDieta(id, dto);
        return ResponseEntity.ok(new DietaResponseDTO(dieta));
    }

    // Endpoint para deletar dieta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDieta(@PathVariable Long id) {
        dietaService.deletarDieta(id);
        return ResponseEntity.noContent().build();
    }
}