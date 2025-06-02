package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.planoacad.PlanoAcademia;
import com.example.NovoTesteCrud.domain.planoacad.dto.PlanoAcademiaRequestDTO;
import com.example.NovoTesteCrud.domain.planoacad.dto.PlanoAcademiaResponseDTO;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
import com.example.NovoTesteCrud.repository.PlanoAcademiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlanoAcademiaServiceTest {

    @Mock
    private PlanoAcademiaRepository planoAcademiaRepository;

    @Mock
    private AcademiaRepository academiaRepository;

    @InjectMocks
    private PlanoAcademiaService planoAcademiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarTodosPlanos() {
        when(planoAcademiaRepository.findAll()).thenReturn(List.of(new PlanoAcademia()));
        List<PlanoAcademia> planos = planoAcademiaService.buscarTodosPlanos();
        assertThat(planos).isNotEmpty();
    }

    @Test
    void deveBuscarPlanosPorAcademia() {
        when(planoAcademiaRepository.findByAcademiaId(1L)).thenReturn(List.of(new PlanoAcademia()));
        List<PlanoAcademia> planos = planoAcademiaService.buscarPlanosPorAcademia(1L);
        assertThat(planos).isNotEmpty();
    }

    @Test
    void deveRegistrarPlano() {
        Academia academia = new Academia();
        academia.setId(1L);

        when(academiaRepository.findById(1L)).thenReturn(Optional.of(academia));
        when(planoAcademiaRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PlanoAcademiaRequestDTO dto = new PlanoAcademiaRequestDTO("Mensal", "Acesso total", 10.0, 1L);
        PlanoAcademiaResponseDTO response = planoAcademiaService.registrarPlano(dto);

        assertThat(response.nome()).isEqualTo("Mensal");
    }

    @Test
    void deveAtualizarPlano() {
        PlanoAcademia plano = new PlanoAcademia();
        plano.setId(2L);

        Academia academia = new Academia();
        academia.setId(1L);

        when(planoAcademiaRepository.findById(2L)).thenReturn(Optional.of(plano));
        when(academiaRepository.findById(1L)).thenReturn(Optional.of(academia));
        when(planoAcademiaRepository.save(any())).thenReturn(plano);

        PlanoAcademiaRequestDTO dto = new PlanoAcademiaRequestDTO("Atualizado", "Nova descrição", 30.0, 1L);
        PlanoAcademiaResponseDTO result = planoAcademiaService.atualizarPlano(2L, dto);

        assertThat(result.nome()).isEqualTo("Atualizado");
    }

    @Test
    void deveLancarErroAoAtualizarPlanoNaoEncontrado() {
        PlanoAcademiaRequestDTO dto = new PlanoAcademiaRequestDTO("Nome", "Desc", 10.0, 1L);
        when(planoAcademiaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> planoAcademiaService.atualizarPlano(1L, dto));
    }

    @Test
    void deveDeletarPlano() {
        PlanoAcademia plano = new PlanoAcademia();
        plano.setId(1L);
        when(planoAcademiaRepository.findById(1L)).thenReturn(Optional.of(plano));
        planoAcademiaService.deletarPlano(1L);
        verify(planoAcademiaRepository, times(1)).delete(plano);
    }

    @Test
    void deveLancarErroAoDeletarPlanoNaoEncontrado() {
        when(planoAcademiaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> planoAcademiaService.deletarPlano(1L));
    }
}
