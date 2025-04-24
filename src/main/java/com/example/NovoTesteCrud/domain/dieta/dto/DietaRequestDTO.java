package com.example.NovoTesteCrud.domain.dieta.dto;

import com.example.NovoTesteCrud.domain.dieta.Dieta.Objetivo;
import jakarta.validation.constraints.*;

public record DietaRequestDTO(

        Long id,

        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "As calorias são obrigatórias")
        @Positive(message = "As calorias devem ser um valor positivo")
        Integer calorias,

        @NotNull(message = "O objetivo é obrigatório")
        Objetivo objetivo,

        // Pelo menos um dos dois deve ser informado
        Long userAcadId,

        Long personalId

) {}
