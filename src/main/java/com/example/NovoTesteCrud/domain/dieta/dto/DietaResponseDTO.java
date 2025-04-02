package com.example.NovoTesteCrud.domain.dieta.dto;

import com.example.NovoTesteCrud.domain.dieta.Dieta;

public record DietaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Integer calorias,
        String objetivo,
        String nomeUsuario
) {
    public DietaResponseDTO(Dieta dieta) {
        this(
                dieta.getId(),
                dieta.getTitulo(),
                dieta.getDescricao(),
                dieta.getCalorias(),
                dieta.getObjetivo().name(),
                dieta.getUserAcad().getUsuario().getNome()
        );
    }
}