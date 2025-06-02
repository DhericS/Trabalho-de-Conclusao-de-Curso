package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.dto.AcademiaRequestDTO;
import com.example.NovoTesteCrud.domain.acad.enums.TipoAcad;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
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

public class AcademiaServiceTest {

    @Mock
    private AcademiaRepository repository;

    @InjectMocks
    private AcademiaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarTodasAcademias() {
        when(repository.findAll()).thenReturn(List.of(new Academia()));
        List<Academia> resultado = service.buscarTodasAcademias();
        assertThat(resultado).isNotEmpty();
    }

    @Test
    void deveBuscarPorId_QuandoExiste() {
        Academia academia = new Academia();
        academia.setId(1L);
        academia.setNome("Nome");
        academia.setEndereco("Endereco");
        academia.setTelefone("Telefone");
        academia.setTipoAcad(TipoAcad.CROSSFIT);
        academia.setImagemUrl("teste.jpg");
        when(repository.findById(1L)).thenReturn(Optional.of(academia));

        Academia resultado = service.buscarPorId(1L);
        assertThat(resultado.getNome()).isEqualTo("Nome");
    }

    @Test
    void deveLancarExcecao_QuandoNaoEncontrarPorId() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void deveRegistrarNovaAcademia() {
        AcademiaRequestDTO dto = new AcademiaRequestDTO("Nova", "Rua", "123", TipoAcad.CONVENCIONAL, "img", null);
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Academia resultado = service.registrarAcademia(dto);
        assertThat(resultado.getNome()).isEqualTo("Nova");
    }

    @Test
    void deveAtualizarAcademiaExistente() {
        Academia existente = new Academia();
        existente.setId(1L);
        existente.setNome("Antiga");
        existente.setEndereco("End");
        existente.setTelefone("Tel");
        existente.setTipoAcad(TipoAcad.CROSSFIT);
        existente.setImagemUrl("url");
        when(repository.findById(1L)).thenReturn(Optional.of(existente));

        AcademiaRequestDTO dto = new AcademiaRequestDTO("Atual", "Av", "321", TipoAcad.CONVENCIONAL, "nova", 1L);
        Academia resultado = service.atualizarAcademia(dto, 1L);

        assertThat(resultado.getNome()).isEqualTo("Atual");
        assertThat(resultado.getEndereco()).isEqualTo("Av");
    }

    @Test
    void deveLancarExcecao_SeNaoEncontrarParaAtualizar() {
        AcademiaRequestDTO dto = new AcademiaRequestDTO("X", "Y", "Z", TipoAcad.CROSSFIT, "img", 1L);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.atualizarAcademia(dto, 1L));
    }

    @Test
    void deveDeletarAcademiaExistente() {
        Academia academia = new Academia();
        academia.setId(1L);
        academia.setNome("Nome");
        academia.setEndereco("Endereco");
        academia.setTelefone("Telefone");
        academia.setTipoAcad(TipoAcad.CROSSFIT);
        academia.setImagemUrl("imagem.jpg");
        when(repository.findById(1L)).thenReturn(Optional.of(academia));

        service.deletarAcademia(1L);

        verify(repository, times(1)).delete(academia);
    }

    @Test
    void deveLancarExcecao_SeNaoEncontrarParaDeletar() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.deletarAcademia(99L));
    }
}