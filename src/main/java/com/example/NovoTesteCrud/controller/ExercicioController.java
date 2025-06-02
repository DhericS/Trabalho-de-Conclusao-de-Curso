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

    // Endpoint para buscar todos os exercícios
    @GetMapping
    public ResponseEntity<List<ExercicioResponseDTO>> buscarTodosExercicios() {
        return ResponseEntity.ok(exercicioService.buscarTodosExercicios());
    }

    // Endpoint para buscar um exercício por grupo muscular
    @GetMapping("/grupo/{grupoMuscular}")
    public ResponseEntity<List<ExercicioResponseDTO>> buscarExerciciosPorGrupoMuscular(@PathVariable GrupoMuscular grupoMuscular) {
        List<ExercicioResponseDTO> exercicios = exercicioService.buscarExerciciosPorGrupoMuscular(grupoMuscular);
        return ResponseEntity.ok(exercicios);
    }

    // Endpoint para registrar um novo exercício
    @PostMapping
    public ResponseEntity<ExercicioResponseDTO> registrarExercicio(@RequestBody @Valid ExercicioRequestDTO dto) {
        var exercicio = exercicioService.registrarExercicio(dto);
        return ResponseEntity.ok(new ExercicioResponseDTO(exercicio));
    }


    // Endpoints para atualizar exercícios
    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> atualizarExercicio(@PathVariable Long id, @RequestBody @Valid ExercicioRequestDTO dto) {
        var atualizado = exercicioService.atualizarExercicio(id, dto);
        return ResponseEntity.ok(new ExercicioResponseDTO(atualizado));
    }

    // Endpoint para deletar um exercício por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable Long id) {
        exercicioService.deletarExercicioPorId(id);
        return ResponseEntity.noContent().build();
    }
}