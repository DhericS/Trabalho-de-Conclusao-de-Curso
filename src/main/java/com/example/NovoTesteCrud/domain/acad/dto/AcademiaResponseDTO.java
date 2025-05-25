package com.example.NovoTesteCrud.domain.acad.dto;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.enums.TipoAcad;
import com.example.NovoTesteCrud.domain.atvd.Atividade;
import com.example.NovoTesteCrud.domain.atvd.dto.AtividadeResponseDTO;
import com.example.NovoTesteCrud.domain.planoacad.dto.PlanoAcademiaResponseDTO;

import java.util.List;

public record AcademiaResponseDTO(
        Long id,
        String nome,
        String endereco,
        String telefone,
        TipoAcad tipoAcad,
        String imagemUrl,
        List<PlanoAcademiaResponseDTO> planos,
        List<AtividadeResponseDTO> atividades
) {
    public AcademiaResponseDTO(Academia academia) {
        this(
                academia.getId(),
                academia.getNome(),
                academia.getEndereco(),
                academia.getTelefone(),
                academia.getTipoAcad(),
                academia.getImagemUrl(),
                academia.getPlanos().stream().map(PlanoAcademiaResponseDTO::new).toList(),
                academia.getActivities() == null ? List.of() :
                        academia.getActivities().stream().map(AtividadeResponseDTO::new).toList()
        );
    }
}
