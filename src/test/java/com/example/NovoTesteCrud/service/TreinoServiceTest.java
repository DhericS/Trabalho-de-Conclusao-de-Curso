package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioRequestDTO;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.treino.Treino;
import com.example.NovoTesteCrud.domain.treino.dto.TreinoRequestDTO;
import com.example.NovoTesteCrud.repository.PersonalRepository;
import com.example.NovoTesteCrud.repository.TreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TreinoServiceTest {

    @Mock
    private TreinoRepository treinoRepository;

    @Mock
    private PersonalRepository personalRepository;

    @InjectMocks
    private TreinoService treinoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrarTreinoComPersonal() {
        Personal personal = new Personal();
        when(personalRepository.findById(1L)).thenReturn(Optional.of(personal));
        when(treinoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        ExercicioRequestDTO exercicio = new ExercicioRequestDTO("Supino", 4, 10, GrupoMuscular.PEITO, null,1L);
        TreinoRequestDTO request = new TreinoRequestDTO("Treino A", "Peito e tríceps", 1L, List.of(exercicio));

        var result = treinoService.registrarTreinos(request);

        assertThat(result.nome()).isEqualTo("Treino A");
    }

    @Test
    void deveAtualizarTreinoExistente() {
        Personal personal = new Personal();
        Treino treinoExistente = new Treino(1L);
        treinoExistente.setExercicios(new ArrayList<>());

        when(treinoRepository.findById(1L)).thenReturn(Optional.of(treinoExistente));
        when(personalRepository.findById(1L)).thenReturn(Optional.of(personal));
        when(treinoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        ExercicioRequestDTO exercicio = new ExercicioRequestDTO("Puxada", 3, 12, GrupoMuscular.COSTA, null,1L);
        TreinoRequestDTO request = new TreinoRequestDTO("Treino B", "Costas e bíceps", 1L, List.of(exercicio));

        var atualizado = treinoService.atualizarTreinos(1L, request);

        assertThat(atualizado.nome()).isEqualTo("Treino B");
    }

    @Test
    void deveLancarExcecao_QuandoTreinoNaoExisteAoAtualizar() {
        when(treinoRepository.findById(99L)).thenReturn(Optional.empty());

        ExercicioRequestDTO exercicio = new ExercicioRequestDTO("Remada", 3, 10, GrupoMuscular.COSTA, null,1L);
        TreinoRequestDTO request = new TreinoRequestDTO("Treino X", "Costas", 1L, List.of(exercicio));

        assertThrows(EntityNotFoundException.class, () -> treinoService.atualizarTreinos(99L, request));
    }

    @Test
    void deveDeletarTreino() {
        Treino treino = new Treino(1L);
        when(treinoRepository.findById(1L)).thenReturn(Optional.of(treino));
        doNothing().when(treinoRepository).delete(treino);

        treinoService.deletarTreinos(1L);
        verify(treinoRepository, times(1)).delete(treino);
    }

    @Test
    void deveLancarExcecao_QuandoTreinoNaoExisteAoDeletar() {
        when(treinoRepository.findById(100L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> treinoService.deletarTreinos(100L));
    }
}