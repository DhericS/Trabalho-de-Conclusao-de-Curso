package com.example.NovoTesteCrud.domain.acad.dto;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.atvd.Atividade;

import java.util.List;

public record AcademiaResponseDTO(Long id, String nome, String endereco, String telefone, List<String> atividades) {
    public AcademiaResponseDTO(Academia academia) {
        this(academia.getId(), academia.getNome(), academia.getEndereco(), academia.getTelefone(),
                academia.getActivities() == null ? List.of() :
                        academia.getActivities().stream().map(Atividade::getNome).toList());
    }
}
