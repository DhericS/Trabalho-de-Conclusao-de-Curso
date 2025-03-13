package com.example.NovoTesteCrud.domain.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByAcademiaId(Long academiaId);
    List<Feedback> findByPersonalId(Long personalId);
    List<Feedback> findByUserId(Long userId);
}
