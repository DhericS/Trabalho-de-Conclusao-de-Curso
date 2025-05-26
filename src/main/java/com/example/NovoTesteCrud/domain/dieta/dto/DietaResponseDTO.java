package com.example.NovoTesteCrud.domain.dieta.dto;

import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.domain.dieta.enums.TipoDieta;

public record DietaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Integer calorias,
        String objetivo,
        TipoDieta tipoDieta,
        String nomeUsuario
) {
    public DietaResponseDTO(Dieta dieta) {
        this(
                dieta.getId(),
                dieta.getTitulo(),
                dieta.getDescricao(),
                dieta.getCalorias(),
                dieta.getObjetivo().name(),
                dieta.getTipoDieta(),
                dieta.getUserAcad() != null
                        ? dieta.getUserAcad().getUsuario().getNome()
                        : dieta.getPersonal().getUsuario().getNome()
        );
    }
}
