package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.feedback.Feedback;

public record FeedbackDTO(Long id, String descricao, Integer estrelas, String userNome, String academiaNome, String personalNome) {
    public FeedbackDTO(Feedback feedback) {
        this(
                feedback.getId(),
                feedback.getDescricao(),
                feedback.getEstrelas(),
                feedback.getUser().getName(),
                feedback.getAcademia() != null ? feedback.getAcademia().getNome() : null,
                feedback.getPersonal() != null ? feedback.getPersonal().getName() : null
        );
    }
}
