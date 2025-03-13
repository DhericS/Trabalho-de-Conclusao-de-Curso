package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
import com.example.NovoTesteCrud.domain.feedback.Feedback;
import com.example.NovoTesteCrud.domain.feedback.FeedbackRepository;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.PersonalRepository;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
import com.example.NovoTesteCrud.domain.feedback.RequestFeedback;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private UserAcadRepository userRepository;

    @Autowired
    private AcademiaRepository academiaRepository;

    @Autowired
    private PersonalRepository personalRepository;

    public List<Feedback> getAllFeedbacks() {
        return repository.findAll();
    }

    public List<Feedback> getFeedbacksByAcademia(Long academiaId) {
        return repository.findByAcademiaId(academiaId);
    }

    public List<Feedback> getFeedbacksByPersonal(Long personalId) {
        return repository.findByPersonalId(personalId);
    }

    public List<Feedback> getFeedbacksByUser(Long userId) {
        return repository.findByUserId(userId);
    }


    @Transactional
    public void registerFeedback(RequestFeedback data) {
        UserAcad user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        Feedback feedback = new Feedback();
        feedback.setDescricao(data.descricao());
        feedback.setEstrelas(data.estrelas());
        feedback.setUser(user);

        if (data.academiaId() != null) {
            Academia academia = academiaRepository.findById(data.academiaId())
                    .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));
            feedback.setAcademia(academia);
        }

        if (data.personalId() != null) {
            Personal personal = personalRepository.findById(data.personalId())
                    .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!"));
            feedback.setPersonal(personal);
        }


        repository.save(feedback);
    }

    @Transactional
    public Feedback updateFeedback(Long feedbackId, RequestFeedback data) {
        Feedback feedback = repository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Feedback não encontrado!"));

        if (!feedback.getUser().getId().equals(data.userId())) {
            throw new SecurityException("Usuário não tem permissão para editar este feedback!");
        }

        feedback.setDescricao(data.descricao());
        feedback.setEstrelas(data.estrelas());

        return repository.save(feedback);
    }

    @Transactional
    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = repository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Feedback não encontrado!"));

        Long userId = feedback.getUser().getId();


        repository.delete(feedback);
    }

}
