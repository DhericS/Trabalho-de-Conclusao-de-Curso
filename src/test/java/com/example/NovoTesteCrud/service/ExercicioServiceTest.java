package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioRequestDTO;
import com.example.NovoTesteCrud.domain.treino.Treino;
import com.example.NovoTesteCrud.repository.ExercicioRepository;
import com.example.NovoTesteCrud.repository.TreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExercicioServiceTest {

    @Mock
    private ExercicioRepository exercicioRepository;

    @Mock
    private TreinoRepository treinoRepository;

    @InjectMocks
    private ExercicioService exercicioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrarExercicio() {
        ExercicioRequestDTO dto = new ExercicioRequestDTO("Supino", 3, 12, GrupoMuscular.PEITO, null,1L);
        Exercicio exercicioSalvo = new Exercicio();
        exercicioSalvo.setId(1L);
        exercicioSalvo.setNome("Supino");

        when(exercicioRepository.save(any())).thenReturn(exercicioSalvo);

        Exercicio resultado = exercicioService.registrarExercicio(dto);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNome()).isEqualTo("Supino");
    }

    @Test
    void deveAtualizarExercicioExistente() {
        Exercicio exercicioExistente = new Exercicio();
        exercicioExistente.setId(1L);
        exercicioExistente.setTreino(new Treino(1L));

        when(exercicioRepository.findById(1L)).thenReturn(Optional.of(exercicioExistente));
        when(exercicioRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ExercicioRequestDTO dto = new ExercicioRequestDTO("Puxada", 4, 10, GrupoMuscular.COSTA, null,1L);
        Exercicio atualizado = exercicioService.atualizarExercicio(1L, dto);

        assertThat(atualizado.getNome()).isEqualTo("Puxada");
        assertThat(atualizado.getGrupoMuscular()).isEqualTo(GrupoMuscular.COSTA);
    }

    @Test
    void deveLancarExcecao_SeNaoEncontrarAoAtualizar() {
        when(exercicioRepository.findById(99L)).thenReturn(Optional.empty());
        ExercicioRequestDTO dto = new ExercicioRequestDTO("Remada", 4, 12, GrupoMuscular.COSTA, null,1L);

        assertThrows(EntityNotFoundException.class, () -> exercicioService.atualizarExercicio(99L, dto));
    }

    @Test
    void deveDeletarExercicio() {
        Exercicio exercicio = new Exercicio();
        exercicio.setId(1L);
        Treino treino = new Treino(1L);

        treino.setExercicios(new ArrayList<>(List.of(exercicio)));
        exercicio.setTreino(treino);

        when(exercicioRepository.findById(1L)).thenReturn(Optional.of(exercicio));
        when(treinoRepository.save(any())).thenReturn(treino);

        exercicioService.deletarExercicioPorId(1L);

        verify(exercicioRepository, times(1)).deleteById(1L);
        verify(treinoRepository, times(1)).save(treino);
    }

    @Test
    void deveLancarExcecao_SeNaoEncontrarAoDeletar() {
        when(exercicioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> exercicioService.deletarExercicioPorId(123L));
    }
}