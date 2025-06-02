package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.atvd.Atividade;
import com.example.NovoTesteCrud.domain.atvd.dto.AtividadeRequestDTO;
import com.example.NovoTesteCrud.domain.atvd.dto.AtividadeResponseDTO;
import com.example.NovoTesteCrud.domain.atvd.enums.DiaSemana;
import com.example.NovoTesteCrud.service.AtividadeService;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AtividadeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AtividadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean private AtividadeService atividadeService;
    @MockBean private JwtFilter jwtFilter;
    @MockBean private JwtUtil jwtUtil;

    @Test
    void deveBuscarTodasAtividades() throws Exception {
        Mockito.when(atividadeService.buscarTodasAtividades()).thenReturn(List.of());
        mockMvc.perform(get("/atividades"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarPorId() throws Exception {
        Mockito.when(atividadeService.buscarPorId(1L)).thenReturn(Mockito.mock(Atividade.class));
        mockMvc.perform(get("/atividades/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarTodasAtividadesPorAcademia() throws Exception {
        Mockito.when(atividadeService.buscarTodasAtividadesPorAcademia(1L)).thenReturn(List.of());
        mockMvc.perform(get("/atividades/academia/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveRegistrarAtividade() throws Exception {
        AtividadeRequestDTO req = new AtividadeRequestDTO(
                "Atividade Teste",
                DiaSemana.SEGUNDA,
                "08:00",
                1L
        );

        Mockito.when(atividadeService.registrarAtividade(any())).thenReturn(Mockito.mock(Atividade.class));

        mockMvc.perform(post("/atividades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarAtividade() throws Exception {
        AtividadeRequestDTO req = new AtividadeRequestDTO(
                "Atualizada",
                DiaSemana.QUARTA,
                "18:30",
                1L
        );

        Mockito.when(atividadeService.atualizarAtividade(any(), eq(1L))).thenReturn(Mockito.mock(Atividade.class));

        mockMvc.perform(put("/atividades/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarAtividade() throws Exception {
        mockMvc.perform(delete("/atividades/1"))
                .andExpect(status().isOk());
    }
}