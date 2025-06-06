package com.example.NovoTesteCrud.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioRequestDTO;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioResponseDTO;
import com.example.NovoTesteCrud.repository.ExercicioRepository;
import com.example.NovoTesteCrud.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ExercicioRepository exercicioRepository;

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

    @PostMapping("/{id}/upload-imagem")
    public ResponseEntity<?> uploadImagemExercicio(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {

        // Pasta no Cloudinary
        String folderPath = "exercicios/";

        // Upload para o Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", folderPath));

        String imageUrl = (String) uploadResult.get("secure_url");

        // Buscar o exercício e atualizar imagem
        Exercicio exercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));

        exercicio.setImagemUrl(imageUrl);
        exercicioRepository.save(exercicio);

        // Retornar URL
        return ResponseEntity.ok(Map.of("url", imageUrl));
    }

}