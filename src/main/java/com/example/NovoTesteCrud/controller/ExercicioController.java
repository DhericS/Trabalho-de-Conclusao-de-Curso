package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioResponseDTO;
import com.example.NovoTesteCrud.service.ExercicioService;
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
}
