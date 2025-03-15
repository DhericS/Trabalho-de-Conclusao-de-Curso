package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
import com.example.NovoTesteCrud.domain.feedback.Feedback;
import com.example.NovoTesteCrud.domain.feedback.FeedbackRepository;
import com.example.NovoTesteCrud.domain.feedback.RequestFeedback;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.PersonalRepository;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private AcademiaRepository academiaRepository;

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private UserAcadRepository userAcadRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getFeedbacksByAcademia(Long academiaId) {
        List<Feedback> feedbacks = feedbackRepository.findByAcademiaId(academiaId);
        if (feedbacks.isEmpty()) {
            throw new EntityNotFoundException("Nenhum feedback encontrado para esta academia.");
        }
        return feedbacks;
    }

    public List<Feedback> getFeedbacksByPersonal(Long personalId) {
        List<Feedback> feedbacks = feedbackRepository.findByPersonalId(personalId);
        if (feedbacks.isEmpty()) {
            throw new EntityNotFoundException("Nenhum feedback encontrado para este personal.");
        }
        return feedbacks;
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback não encontrado!"));
    }

    @Transactional
    public Feedback registerFeedback(RequestFeedback data) {
        UserAcad user = userAcadRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + data.userId() + " não encontrado!"));

        Feedback feedback = new Feedback();
        feedback.setDescricao(data.descricao());
        feedback.setEstrelas(data.estrelas());
        feedback.setUser(user);

        if (data.academiaId() != null) {
            Academia academia = academiaRepository.findById(data.academiaId())
                    .orElseThrow(() -> new EntityNotFoundException("Academia com ID " + data.academiaId() + " não encontrada!"));
            feedback.setAcademia(academia);
        }

        if (data.personalId() != null) {
            Personal personal = personalRepository.findById(data.personalId())
                    .orElseThrow(() -> new EntityNotFoundException("Personal com ID " + data.personalId() + " não encontrado!"));
            feedback.setPersonal(personal);
        }

        return feedbackRepository.save(feedback);
    }

    @Transactional
    public Feedback updateFeedback(Long feedbackId, RequestFeedback data) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Feedback não encontrado!"));

        feedback.setDescricao(data.descricao());
        feedback.setEstrelas(data.estrelas());

        return feedback;
    }

    @Transactional
    public void deleteFeedback(Long feedbackId, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Feedback não encontrado!"));

        if (!feedback.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Você não tem permissão para excluir este feedback.");
        }

        feedbackRepository.delete(feedback);
    }
}
