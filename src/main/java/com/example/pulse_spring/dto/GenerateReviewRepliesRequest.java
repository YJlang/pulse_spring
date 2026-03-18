package com.example.pulse_spring.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerateReviewRepliesRequest {
    @Builder.Default
    private List<ReviewItemDto> reviews = new ArrayList<>();
    private ReviewManagementSettingsDto settings;
}
