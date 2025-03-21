package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.ExercicioRepository;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioResponseDTO;
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
}
