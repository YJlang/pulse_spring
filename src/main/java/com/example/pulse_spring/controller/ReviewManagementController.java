package com.example.pulse_spring.controller;

import com.example.pulse_spring.dto.*;
import com.example.pulse_spring.service.ReviewManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/review-management")
@RequiredArgsConstructor
public class ReviewManagementController {

    private final ReviewManagementService reviewManagementService;

    @GetMapping("/context")
    public ResponseEntity<?> getContext(Authentication authentication) {
        try {
            return ResponseEntity.ok(reviewManagementService.getContext(authentication.getName()));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/settings")
    public ResponseEntity<?> saveSettings(Authentication authentication, @RequestBody ReviewManagementSettingsDto request) {
        try {
            return ResponseEntity.ok(reviewManagementService.saveSettings(authentication.getName(), request));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/templates")
    public ResponseEntity<?> createTemplate(Authentication authentication, @RequestBody ReviewTemplateDto request) {
        try {
            return ResponseEntity.ok(reviewManagementService.createTemplate(authentication.getName(), request));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/templates/{templateId}")
    public ResponseEntity<?> updateTemplate(
            Authentication authentication,
            @PathVariable Long templateId,
            @RequestBody ReviewTemplateDto request
    ) {
        try {
            return ResponseEntity.ok(reviewManagementService.updateTemplate(authentication.getName(), templateId, request));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/templates/{templateId}")
    public ResponseEntity<?> deleteTemplate(Authentication authentication, @PathVariable Long templateId) {
        try {
            reviewManagementService.deleteTemplate(authentication.getName(), templateId);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/replies/generate")
    public ResponseEntity<?> generateReplies(Authentication authentication, @RequestBody GenerateReviewRepliesRequest request) {
        try {
            return ResponseEntity.ok(reviewManagementService.generateReplies(authentication.getName(), request));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
