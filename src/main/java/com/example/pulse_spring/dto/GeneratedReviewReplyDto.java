package com.example.pulse_spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneratedReviewReplyDto {
    private String id;
    private String reviewId;
    private String content;
    private boolean isRecommended;
}
