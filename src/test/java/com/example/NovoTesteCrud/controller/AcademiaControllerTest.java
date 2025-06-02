package com.example.NovoTesteCrud.controller;

import com.cloudinary.Cloudinary;
import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.dto.*;
import com.example.NovoTesteCrud.domain.acad.enums.Estrutura;
import com.example.NovoTesteCrud.domain.acad.enums.Servicos;
import com.example.NovoTesteCrud.domain.acad.enums.TipoAcad;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
import com.example.NovoTesteCrud.service.AcademiaService;
import com.example.NovoTesteCrud.config.security.JwtFilter;
import com.example.NovoTesteCrud.config.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AcademiaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AcademiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private AcademiaService academiaService;
    @MockBean private AcademiaRepository academiaRepository;
    @MockBean private Cloudinary cloudinary;

    @MockBean private JwtUtil jwtUtil;
    @MockBean private JwtFilter jwtFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveBuscarTodasAsAcademias() throws Exception {
        Mockito.when(academiaService.buscarTodasAcademias()).thenReturn(List.of());
        mockMvc.perform(get("/academia"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarPorId() throws Exception {
        Mockito.when(academiaService.buscarPorId(1L)).thenReturn(Mockito.mock(Academia.class));
        mockMvc.perform(get("/academia/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarComFiltro() throws Exception {
        Mockito.when(academiaService.buscarTodasAcademiasFiltradas(any())).thenReturn(List.of());
        mockMvc.perform(get("/academia/filtro"))
                .andExpect(status().isOk());
    }

    @Test
    void deveRegistrarAcademia() throws Exception {
        AcademiaRequestDTO req = new AcademiaRequestDTO(
                "Academia Teste",
                "Rua XPTO",
                "11999999999",
                TipoAcad.CROSSFIT,
                "https://cdn.imagem.jpg",
                null
        );

        Mockito.when(academiaService.registrarAcademia(any())).thenReturn(Mockito.mock(Academia.class));

        mockMvc.perform(post("/academia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarAcademia() throws Exception {
        AcademiaRequestDTO req = new AcademiaRequestDTO(
                "Nova",
                "Av Brasil",
                "11900000000",
                TipoAcad.CONVENCIONAL,
                "https://novaimg.com",
                1L
        );

        Mockito.when(academiaService.atualizarAcademia(any(), eq(1L))).thenReturn(Mockito.mock(Academia.class));

        mockMvc.perform(put("/academia/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarAcademia() throws Exception {
        mockMvc.perform(delete("/academia/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarAcademiasExternas() throws Exception {
        Mockito.when(academiaService.buscarAcademiasProximas(any())).thenReturn(List.of());
        mockMvc.perform(get("/academia/externas")
                        .param("endereco", "Av Paulista"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarDetalhesExternos() throws Exception {
        Mockito.when(academiaService.detalhesComoDTO(any())).thenReturn(Mockito.mock(AcademiaDetalhesDTO.class));
        mockMvc.perform(get("/academia/externas-detalhes")
                        .param("placeId", "abc123"))
                .andExpect(status().isOk());
    }

    @Test
    void deveFazerUploadImagem() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "img.jpg", MediaType.IMAGE_JPEG_VALUE, "fake-img".getBytes());

        var uploaderMock = Mockito.mock(com.cloudinary.Uploader.class);
        Mockito.when(cloudinary.uploader()).thenReturn(uploaderMock);
        Mockito.when(uploaderMock.upload(Mockito.any(), Mockito.any()))
                .thenReturn(Map.of("secure_url", "http://img.com/img.jpg"));

        Mockito.when(academiaRepository.findById(1L)).thenReturn(Optional.of(new Academia(1L)));

        mockMvc.perform(multipart("/academia/1/upload-imagem").file(file))
                .andExpect(status().isOk());
    }

    @Test
    void deveFazerUploadImagemTemp() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "temp.jpg", MediaType.IMAGE_JPEG_VALUE, "tmp".getBytes());

        var uploaderMock = Mockito.mock(com.cloudinary.Uploader.class);
        Mockito.when(cloudinary.uploader()).thenReturn(uploaderMock);
        Mockito.when(uploaderMock.upload(Mockito.any(), Mockito.any()))
                .thenReturn(Map.of("secure_url", "http://img.com/tmp.jpg"));

        mockMvc.perform(multipart("/academia/upload-imagem-temp").file(file))
                .andExpect(status().isOk());
    }
}