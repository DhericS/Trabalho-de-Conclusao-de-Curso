package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.dto.ExercicioDTO;
import com.example.NovoTesteCrud.dto.TreinoDTO;
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
    public ResponseEntity<List<ExercicioDTO>> getAllExercicios() {
        return ResponseEntity.ok(exercicioService.getAllExercicios());
    }

    @GetMapping("/grupo/{grupoMuscular}")
    public ResponseEntity<List<ExercicioDTO>> getExerciciosByGrupoMuscular(@PathVariable GrupoMuscular grupoMuscular) {
        List<ExercicioDTO> exercicios = exercicioService.getExerciciosByGrupoMuscular(grupoMuscular);
        return ResponseEntity.ok(exercicios);
    }
}
