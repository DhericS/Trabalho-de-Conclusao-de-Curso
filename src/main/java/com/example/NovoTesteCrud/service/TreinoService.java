package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.treino.*;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
import com.example.NovoTesteCrud.dto.TreinoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private UserAcadRepository userRepository;

    @Transactional
    public TreinoDTO registerTreino(RequestTreino data) {
        UserAcad user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        Treino treino = new Treino();
        treino.setNome(data.nome());
        treino.setDescricao(data.descricao());
        treino.setUser(user);

        List<Exercicio> exercicios = data.exercicios().stream().map(req -> {
            Exercicio exercicio = new Exercicio();
            exercicio.setNome(req.nome());
            exercicio.setSeries(req.series());
            exercicio.setRepeticoes(req.repeticoes());
            exercicio.setGrupoMuscular(req.grupoMuscular());
            exercicio.setTreino(treino);
            return exercicio;
        }).toList();

        treino.setExercicios(exercicios);
        treinoRepository.save(treino);

        return new TreinoDTO(treino);
    }

    public List<TreinoDTO> getAllTreinos() {
        return treinoRepository.findAll().stream().map(TreinoDTO::new).toList();
    }

    @Transactional
    public TreinoDTO updateTreino(Long id, RequestTreino data) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treino não encontrado!"));

        UserAcad user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        treino.setNome(data.nome());
        treino.setDescricao(data.descricao());
        treino.setUser(user);

        treino.getExercicios().clear();

        List<Exercicio> novosExercicios = data.exercicios().stream().map(req -> {
            Exercicio exercicio = new Exercicio();
            exercicio.setNome(req.nome());
            exercicio.setSeries(req.series());
            exercicio.setRepeticoes(req.repeticoes());
            exercicio.setGrupoMuscular(req.grupoMuscular());
            exercicio.setTreino(treino);
            return exercicio;
        }).toList();

        treino.getExercicios().addAll(novosExercicios);

        treinoRepository.save(treino);
        return new TreinoDTO(treino);
    }
    @Transactional
    public void deleteTreino(Long id) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treino não encontrado!"));
        treinoRepository.delete(treino);
    }
}
