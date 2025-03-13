package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.RequestAcademia;
import com.example.NovoTesteCrud.dto.AcademiaDTO;
import com.example.NovoTesteCrud.service.AcademiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academias")
public class AcademiaController {

    @Autowired
    private AcademiaService academiaService;

    @GetMapping
    public ResponseEntity<List<AcademiaDTO>> getAllAcademias() {
        List<AcademiaDTO> academias = academiaService.getAllAcademias().stream()
                .map(AcademiaDTO::new)
                .toList();
        return ResponseEntity.ok(academias);
    }

    @PostMapping
    public ResponseEntity<Void> registerAcademia(@RequestBody @Valid RequestAcademia data) {
        academiaService.registerAcademia(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Academia> updateAcademia(@RequestBody @Valid RequestAcademia data, @RequestParam Long id) {
        return ResponseEntity.ok(academiaService.updateAcademia(data, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademia(@PathVariable Long id) {
        academiaService.deleteAcademia(id);
        return ResponseEntity.noContent().build();
    }
}
