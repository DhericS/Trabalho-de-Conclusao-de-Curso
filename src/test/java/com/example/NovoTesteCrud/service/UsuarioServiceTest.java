package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.dto.AcademiaRequestDTO;
import com.example.NovoTesteCrud.domain.acad.enums.TipoAcad;
import com.example.NovoTesteCrud.domain.acad.dto.AcademiaResponseDTO;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.repository.UserAcadAdminRepository;
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

public class UsuarioServiceTest {

    @Mock
    private UserAcadAdminRepository userAcadAdminRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAssociarAcademiaAoUserAcadAdmin() {
        UserAcadAdmin user = new UserAcadAdmin();
        user.setId(1L);

        when(userAcadAdminRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userAcadAdminRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AcademiaRequestDTO dto = new AcademiaRequestDTO("Academia XP", "Rua A", "11999999999", TipoAcad.CROSSFIT, "img.png", 1L);

        AcademiaResponseDTO response = usuarioService.associarAcademiaAoUserAcadAdmin(1L, dto);

        assertThat(response.nome()).isEqualTo("Academia XP");
        assertThat(user.getAcademia()).isNotNull();
    }

    @Test
    void deveEditarAcademiaDoUserAcadAdmin() {
        Academia academia = new Academia();
        academia.setId(1L);
        academia.setNome("Antigo");

        UserAcadAdmin user = new UserAcadAdmin();
        user.setId(1L);
        user.setAcademia(academia);

        when(userAcadAdminRepository.findById(1L)).thenReturn(Optional.of(user));

        AcademiaRequestDTO dto = new AcademiaRequestDTO("Nova", "Rua B", "1122222222", TipoAcad.CROSSFIT, "nova.png", 1L);

        AcademiaResponseDTO response = usuarioService.editarAcademiaDoUserAcadAdmin(1L, dto);

        assertThat(response.nome()).isEqualTo("Nova");
        assertThat(response.telefone()).isEqualTo("1122222222");
    }

    @Test
    void deveLancarExcecao_QuandoNaoEncontrarUserAcadAdmin_paraEdicao() {
        when(userAcadAdminRepository.findById(999L)).thenReturn(Optional.empty());

        AcademiaRequestDTO dto = new AcademiaRequestDTO("Teste", "Rua Z", "1100000000", TipoAcad.CONVENCIONAL, "img.png", 999L);

        assertThrows(EntityNotFoundException.class, () -> usuarioService.editarAcademiaDoUserAcadAdmin(999L, dto));
    }

    @Test
    void deveLancarExcecao_QuandoUserNaoPossuiAcademia() {
        UserAcadAdmin user = new UserAcadAdmin();
        user.setId(1L);
        user.setAcademia(null);

        when(userAcadAdminRepository.findById(1L)).thenReturn(Optional.of(user));

        AcademiaRequestDTO dto = new AcademiaRequestDTO("Academia", "Rua K", "1133333333", TipoAcad.CONVENCIONAL, "img2.png", 1L);

        assertThrows(EntityNotFoundException.class, () -> usuarioService.editarAcademiaDoUserAcadAdmin(1L, dto));
    }
}

