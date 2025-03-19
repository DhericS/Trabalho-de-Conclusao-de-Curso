package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.ExercicioRepository;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.dto.ExercicioDTO;
import com.example.NovoTesteCrud.dto.TreinoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<ExercicioDTO> getAllExercicios() {
        return exercicioRepository.findAll().stream().map(ExercicioDTO::new).toList();
    }

    public List<ExercicioDTO> getExerciciosByGrupoMuscular(GrupoMuscular grupoMuscular) {
        List<Exercicio> exercicios = exercicioRepository.findByGrupoMuscular(grupoMuscular);
        return exercicios.stream().map(ExercicioDTO::new).collect(Collectors.toList());
    }
}
