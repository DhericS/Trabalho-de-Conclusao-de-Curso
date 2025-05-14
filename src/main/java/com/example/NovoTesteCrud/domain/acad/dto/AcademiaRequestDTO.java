package com.example.NovoTesteCrud.domain.acad.dto;

import com.example.NovoTesteCrud.domain.acad.enums.TipoAcad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AcademiaRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O endereço é obrigatório") String endereco,
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        @NotNull(message = "O tipo da academia é obrigatório") TipoAcad tipoAcad,
        Long id
) {}
