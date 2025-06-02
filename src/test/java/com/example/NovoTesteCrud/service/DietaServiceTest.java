package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.domain.dieta.Dieta.Objetivo;
import com.example.NovoTesteCrud.domain.dieta.dto.DietaRequestDTO;
import com.example.NovoTesteCrud.domain.dieta.enums.TipoDieta;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.repository.DietaRepository;
import com.example.NovoTesteCrud.repository.PersonalRepository;
import com.example.NovoTesteCrud.repository.UserAcadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DietaServiceTest {

    @Mock
    private DietaRepository dietaRepository;
    @Mock
    private UserAcadRepository userAcadRepository;
    @Mock
    private PersonalRepository personalRepository;

    @InjectMocks
    private DietaService dietaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodasAsDietas() {
        when(dietaRepository.findAll()).thenReturn(java.util.List.of(new Dieta()));
        assertThat(dietaService.listarTodasDieta()).isNotEmpty();
    }

    @Test
    void deveCriarDietaParaUserAcad() {
        UserAcad userAcad = new UserAcad();
        when(userAcadRepository.findById(1L)).thenReturn(Optional.of(userAcad));
        when(dietaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        DietaRequestDTO dto = new DietaRequestDTO(null, "Dieta UserAcad", "desc", 1200, Objetivo.BULKING, TipoDieta.BULKING, 1L, null);
        Dieta resultado = dietaService.criarDieta(dto);

        assertThat(resultado.getTitulo()).isEqualTo("Dieta UserAcad");
        assertThat(resultado.getUserAcad()).isNotNull();
    }

    @Test
    void deveCriarDietaParaPersonal() {
        Personal personal = new Personal();
        when(personalRepository.findById(2L)).thenReturn(Optional.of(personal));
        when(dietaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        DietaRequestDTO dto = new DietaRequestDTO(null, "Dieta Personal", "desc", 1500, Objetivo.CUTTING, TipoDieta.CUTTING, null, 2L);
        Dieta resultado = dietaService.criarDieta(dto);

        assertThat(resultado.getTitulo()).isEqualTo("Dieta Personal");
        assertThat(resultado.getPersonal()).isNotNull();
    }

    @Test
    void deveAtualizarDietaExistente() {
        Dieta dieta = new Dieta();
        dieta.setId(1L);
        dieta.setTitulo("Antiga");

        when(dietaRepository.findById(1L)).thenReturn(Optional.of(dieta));
        when(dietaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        DietaRequestDTO dto = new DietaRequestDTO(null, "Nova", "desc", 1000, Objetivo.BULKING, TipoDieta.BULKING, null, null);
        Dieta atualizada = dietaService.atualizarDieta(1L, dto);

        assertThat(atualizada.getTitulo()).isEqualTo("Nova");
    }

    @Test
    void deveLancarExcecao_QuandoDietaNaoExisteAoAtualizar() {
        when(dietaRepository.findById(99L)).thenReturn(Optional.empty());
        DietaRequestDTO dto = new DietaRequestDTO(null, "Nova", "desc", 1000, Objetivo.BULKING, TipoDieta.BULKING, null, null);

        assertThrows(EntityNotFoundException.class, () -> dietaService.atualizarDieta(99L, dto));
    }

    @Test
    void deveDeletarDieta() {
        doNothing().when(dietaRepository).deleteById(1L);
        dietaService.deletarDieta(1L);
        verify(dietaRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveBuscarDietaPorId() {
        Dieta dieta = new Dieta();
        dieta.setId(1L);
        when(dietaRepository.findById(1L)).thenReturn(Optional.of(dieta));

        Dieta resultado = dietaService.buscarDietaPorId(1L);
        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    void deveLancarExcecao_SeNaoEncontrarAoBuscarPorId() {
        when(dietaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> dietaService.buscarDietaPorId(99L));
    }
}
