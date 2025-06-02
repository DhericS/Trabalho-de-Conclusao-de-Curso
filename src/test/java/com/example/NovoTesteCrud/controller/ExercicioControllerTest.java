package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.exercicios.Exercicio;
import com.example.NovoTesteCrud.domain.exercicios.GrupoMuscular;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioRequestDTO;
import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioResponseDTO;
import com.example.NovoTesteCrud.service.ExercicioService;
import com.example.NovoTesteCrud.config.security.JwtFilter;
import com.example.NovoTesteCrud.config.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ExercicioController.class)
@AutoConfigureMockMvc(addFilters = false) // ignora o JwtFilter e segurança
public class ExercicioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExercicioService exercicioService;

    // Adicionados para evitar falha no contexto
    @MockBean private JwtFilter jwtFilter;
    @MockBean private JwtUtil jwtUtil;

    @Test
    void deveBuscarTodosExercicios() throws Exception {
        Mockito.when(exercicioService.buscarTodosExercicios()).thenReturn(List.of());
        mockMvc.perform(get("/exercicios"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarPorGrupoMuscular() throws Exception {
        Mockito.when(exercicioService.buscarExerciciosPorGrupoMuscular(any())).thenReturn(List.of());
        mockMvc.perform(get("/exercicios/grupo/PEITO"))
                .andExpect(status().isOk());
    }

    @Test
    void deveRegistrarExercicio() throws Exception {
        ExercicioRequestDTO dto = new ExercicioRequestDTO("Supino Reto", 4, 12, GrupoMuscular.PEITO, 1L);
        Exercicio mockExercicio = Mockito.mock(Exercicio.class);

        Mockito.when(exercicioService.registrarExercicio(any())).thenReturn(mockExercicio);

        mockMvc.perform(post("/exercicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarExercicio() throws Exception {
        ExercicioRequestDTO dto = new ExercicioRequestDTO("Crucifixo", 3, 10, GrupoMuscular.PEITO, 1L);
        Exercicio mockExercicio = Mockito.mock(Exercicio.class);

        Mockito.when(exercicioService.atualizarExercicio(eq(1L), any())).thenReturn(mockExercicio);

        mockMvc.perform(put("/exercicios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarExercicio() throws Exception {
        mockMvc.perform(delete("/exercicios/1"))
                .andExpect(status().isNoContent());
    }
}