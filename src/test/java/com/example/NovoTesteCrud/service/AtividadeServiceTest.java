package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.atvd.Atividade;
import com.example.NovoTesteCrud.domain.atvd.dto.AtividadeRequestDTO;
import com.example.NovoTesteCrud.domain.atvd.enums.DiaSemana;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
import com.example.NovoTesteCrud.repository.AtividadeRepository;
import com.example.NovoTesteCrud.repository.UserAcadAdminRepository;
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

public class AtividadeServiceTest {

    @Mock
    private AtividadeRepository atividadeRepository;

    @Mock
    private AcademiaRepository academiaRepository;

    @Mock
    private UserAcadAdminRepository userAcadAdminRepository;

    @InjectMocks
    private AtividadeService atividadeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrarAtividade() {
        Academia academia = new Academia();
        when(academiaRepository.findById(1L)).thenReturn(Optional.of(academia));
        when(atividadeRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        AtividadeRequestDTO dto = new AtividadeRequestDTO("Yoga", DiaSemana.SEGUNDA, "08:00", 1L);
        Atividade atividade = atividadeService.registrarAtividade(dto);

        assertThat(atividade.getNome()).isEqualTo("Yoga");
        assertThat(atividade.getAcademia()).isEqualTo(academia);
    }

    @Test
    void deveAtualizarAtividade() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setNome("Spinning");

        when(atividadeRepository.findById(1L)).thenReturn(Optional.of(atividade));
        when(atividadeRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        AtividadeRequestDTO dto = new AtividadeRequestDTO("Pilates", DiaSemana.SEGUNDA, "08:00", 1L);
        Atividade atualizada = atividadeService.atualizarAtividade(dto, 1L);

        assertThat(atualizada.getNome()).isEqualTo("Pilates");
    }

    @Test
    void deveLancarExcecao_QuandoAtualizarAtividadeInexistente() {
        when(atividadeRepository.findById(999L)).thenReturn(Optional.empty());
        AtividadeRequestDTO dto = new AtividadeRequestDTO("Pilates", DiaSemana.QUINTA, "08:00", 1L);

        assertThrows(EntityNotFoundException.class, () -> atividadeService.atualizarAtividade(dto, 999L));
    }

    @Test
    void deveBuscarPorId() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        when(atividadeRepository.findById(1L)).thenReturn(Optional.of(atividade));

        Atividade resultado = atividadeService.buscarPorId(1L);
        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    void deveDeletarAtividade() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        when(atividadeRepository.findById(1L)).thenReturn(Optional.of(atividade));
        doNothing().when(atividadeRepository).delete(atividade);

        atividadeService.deletarAtividade(1L);
        verify(atividadeRepository, times(1)).delete(atividade);
    }

    @Test
    void deveLancarExcecao_QuandoAtividadeNaoExisteAoDeletar() {
        when(atividadeRepository.findById(888L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> atividadeService.deletarAtividade(888L));
    }
}