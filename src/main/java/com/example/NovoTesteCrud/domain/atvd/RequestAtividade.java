package com.example.NovoTesteCrud.domain.atvd;

import jakarta.validation.constraints.NotBlank;

public record RequestAtividade(
        @NotBlank(message = "O nome é obrigatorio") String name,
        Long academiaId) {
}
