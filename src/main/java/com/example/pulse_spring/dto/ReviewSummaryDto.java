package com.example.pulse_spring.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSummaryDto {
    private double averageRating;
    private int totalReviews;
    @Builder.Default
    private List<ReviewSummaryMetricDto> evaluationMetrics = new ArrayList<>();
}
