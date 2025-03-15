package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.dto.PlanoAcademiaDTO;
import com.example.NovoTesteCrud.domain.planoacad.RequestPlanoAcademia;
import com.example.NovoTesteCrud.service.PlanoAcademiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<Void> registerPlano(@RequestBody @Valid RequestPlanoAcademia data) {
        planoAcademiaService.registerPlano(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoAcademiaDTO> updatePlano(@PathVariable Long id, @RequestBody @Valid RequestPlanoAcademia data) {
        PlanoAcademiaDTO updatedPlano = new PlanoAcademiaDTO(planoAcademiaService.updatePlano(id, data));
        return ResponseEntity.ok(updatedPlano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable Long id) {
        planoAcademiaService.deletePlano(id);
        return ResponseEntity.noContent().build();
    }
}
