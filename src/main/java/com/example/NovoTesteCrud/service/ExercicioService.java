package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioRequestDTO;
import com.example.NovoTesteCrud.domain.treino.Treino;
import com.example.NovoTesteCrud.repository.ExercicioRepository;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<ExercicioResponseDTO> buscarTodosExercicios() {
        return exercicioRepository.findAll().stream().map(ExercicioResponseDTO::new).toList();
    }

    public List<ExercicioResponseDTO> buscarExerciciosPorGrupoMuscular(GrupoMuscular grupoMuscular) {
        List<Exercicio> exercicios = exercicioRepository.findByGrupoMuscular(grupoMuscular);
        return exercicios.stream().map(ExercicioResponseDTO::new).collect(Collectors.toList());
    }

    public Exercicio registrarExercicio(ExercicioRequestDTO dto) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(dto.nome());
        exercicio.setSeries(dto.series());
        exercicio.setRepeticoes(dto.repeticoes());
        exercicio.setGrupoMuscular(dto.grupoMuscular());
        exercicio.setTreino(new Treino(dto.treinoId()));
        return exercicioRepository.save(exercicio);
    }

    public Exercicio atualizarExercicio(Long id, ExercicioRequestDTO dto) {
        Exercicio exercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exercício não encontrado com ID: " + id));
        exercicio.setNome(dto.nome());
        exercicio.setSeries(dto.series());
        exercicio.setRepeticoes(dto.repeticoes());
        exercicio.setGrupoMuscular(dto.grupoMuscular());
        exercicio.setTreino(new Treino(dto.treinoId()));
        return exercicioRepository.save(exercicio);
    }

    public void deletarExercicioPorId(Long id) {
        if (!exercicioRepository.existsById(id)) {
            throw new EntityNotFoundException("Exercício não encontrado com ID: " + id);
        }
        exercicioRepository.deleteById(id);
    }
}