package com.example.NovoTesteCrud.domain.dieta.dto;

import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.domain.dieta.Dieta.Objetivo;
import jakarta.validation.constraints.*;

public record DietaRequestDTO(
         Long id,
         String titulo,
         String descricao,
         Integer calorias,
         Objetivo objetivo,
         Long userAcadId
) {}
