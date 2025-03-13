package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.dto.FeedbackDTO;
import com.example.NovoTesteCrud.domain.feedback.RequestFeedback;
import com.example.NovoTesteCrud.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacks()
                .stream()
                .map(FeedbackDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByAcademia(@PathVariable Long academiaId) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByAcademia(academiaId)
                .stream()
                .map(FeedbackDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByPersonal(@PathVariable Long personalId) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByPersonal(personalId)
                .stream()
                .map(FeedbackDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByUser(@PathVariable Long userId) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByUser(userId)
                .stream()
                .map(FeedbackDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(feedbacks);
    }
    @PostMapping
    public ResponseEntity<Void> registerFeedback(@RequestBody @Valid RequestFeedback data) {
        feedbackService.registerFeedback(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(
            @PathVariable Long id,
            @RequestBody @Valid RequestFeedback data) {

        FeedbackDTO updatedFeedback = new FeedbackDTO(feedbackService.updateFeedback(id, data));
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
