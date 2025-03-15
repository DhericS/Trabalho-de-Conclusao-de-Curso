package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.RequestAcademia;
import com.example.NovoTesteCrud.dto.AcademiaDTO;
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
    public ResponseEntity<List<AcademiaDTO>> getAllAcademias() {
        List<AcademiaDTO> academias = academiaService.getAllAcademias().stream()
                .map(AcademiaDTO::new)
                .toList();
        return ResponseEntity.ok(academias);
    }

    @PostMapping
    public ResponseEntity<AcademiaDTO> registerAcademia(@RequestBody @Valid RequestAcademia data) {
        Academia academia = academiaService.registerAcademia(data);
        AcademiaDTO response = new AcademiaDTO(academia);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Academia> updateAcademia(@PathVariable Long id, @RequestBody @Valid RequestAcademia data) {
        return ResponseEntity.ok(academiaService.updateAcademia(data, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAcademia(@PathVariable Long id) {
        academiaService.deleteAcademia(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Academia deletada com sucesso!");

        return ResponseEntity.ok(response);
    }

}
