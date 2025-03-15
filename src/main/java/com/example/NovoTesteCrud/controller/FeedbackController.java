package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.feedback.Feedback;
import com.example.NovoTesteCrud.domain.feedback.RequestFeedback;
import com.example.NovoTesteCrud.dto.FeedbackDTO;
import com.example.NovoTesteCrud.service.FeedbackService;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacks()
                .stream().map(FeedbackDTO::new).toList();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable Long id) {
        try {
            FeedbackDTO feedback = new FeedbackDTO(feedbackService.getFeedbackById(id));
            return ResponseEntity.ok(feedback);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<?> getFeedbacksByAcademia(@PathVariable Long academiaId) {
        try {
            List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByAcademia(academiaId)
                    .stream().map(FeedbackDTO::new).toList();
            return ResponseEntity.ok(feedbacks);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<?> getFeedbacksByPersonal(@PathVariable Long personalId) {
        try {
            List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByPersonal(personalId)
                    .stream().map(FeedbackDTO::new).toList();
            return ResponseEntity.ok(feedbacks);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> registerFeedback(@RequestBody @Valid RequestFeedback data) {
        Feedback feedback = feedbackService.registerFeedback(data);
        return ResponseEntity.ok(new FeedbackDTO(feedback));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Long id, @RequestBody @Valid RequestFeedback data) {
        Feedback feedback = feedbackService.updateFeedback(id, data);
        return ResponseEntity.ok(new FeedbackDTO(feedback));
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Map<String, String>> deleteFeedback(@PathVariable Long id, @PathVariable Long userId) {
        feedbackService.deleteFeedback(id, userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Feedback deletado com sucesso!");
        return ResponseEntity.ok(response);
    }


}
