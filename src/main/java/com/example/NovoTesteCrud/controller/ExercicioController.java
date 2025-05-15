package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioRequestDTO;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioResponseDTO;
import com.example.NovoTesteCrud.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public ResponseEntity<List<ExercicioResponseDTO>> buscarTodosExercicios() {
        return ResponseEntity.ok(exercicioService.buscarTodosExercicios());
    }

    @GetMapping("/grupo/{grupoMuscular}")
    public ResponseEntity<List<ExercicioResponseDTO>> buscarExerciciosPorGrupoMuscular(@PathVariable GrupoMuscular grupoMuscular) {
        List<ExercicioResponseDTO> exercicios = exercicioService.buscarExerciciosPorGrupoMuscular(grupoMuscular);
        return ResponseEntity.ok(exercicios);
    }
    @PostMapping
    public ResponseEntity<ExercicioResponseDTO> registrarExercicio(@RequestBody @Valid ExercicioRequestDTO dto) {
        var exercicio = exercicioService.registrarExercicio(dto);
        return ResponseEntity.ok(new ExercicioResponseDTO(exercicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> atualizarExercicio(@PathVariable Long id, @RequestBody @Valid ExercicioRequestDTO dto) {
        var atualizado = exercicioService.atualizarExercicio(id, dto);
        return ResponseEntity.ok(new ExercicioResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable Long id) {
        exercicioService.deletarExercicioPorId(id);
        return ResponseEntity.noContent().build();
    }
}