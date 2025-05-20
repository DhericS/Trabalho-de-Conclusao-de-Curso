package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.planoacad.dto.PlanoAcademiaResponseDTO;
import com.example.NovoTesteCrud.domain.planoacad.dto.PlanoAcademiaRequestDTO;
import com.example.NovoTesteCrud.service.PlanoAcademiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('USERADMIN')")
    @GetMapping
    public ResponseEntity<List<PlanoAcademiaResponseDTO>> buscarTodosPlanos() {
        List<PlanoAcademiaResponseDTO> planos = planoAcademiaService.buscarTodosPlanos()
                .stream()
                .map(PlanoAcademiaResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(planos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoAcademiaResponseDTO> buscarPorId(@PathVariable Long id) {
        return planoAcademiaService.buscarPorId(id)
                .map(plano -> ResponseEntity.ok(new PlanoAcademiaResponseDTO(plano)))
                .orElse(ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN', 'USERACAD', 'PERSONAL')")
    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<List<PlanoAcademiaResponseDTO>> buscarPlanosPorAcademia(@PathVariable Long academiaId) {
        List<PlanoAcademiaResponseDTO> planos = planoAcademiaService.buscarPlanosPorAcademia(academiaId)
                .stream()
                .map(PlanoAcademiaResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(planos);
    }

    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN')")
    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarPlano(@RequestBody @Valid PlanoAcademiaRequestDTO data) {
        PlanoAcademiaResponseDTO planoDTO = planoAcademiaService.registrarPlano(data);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Plano de academia cadastrado com sucesso!");
        response.put("plano", planoDTO);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarPlano(@PathVariable Long id, @RequestBody @Valid PlanoAcademiaRequestDTO data) {
        PlanoAcademiaResponseDTO updatedPlano = planoAcademiaService.atualizarPlano(id, data);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Plano de academia atualizado com sucesso!");
        response.put("plano", updatedPlano);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarPlano(@PathVariable Long id) {
        planoAcademiaService.deletarPlano(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Plano de academia deletado com sucesso!");

        return ResponseEntity.ok(response);
    }

}
