package com.example.pulse_spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSummaryMetricDto {
    private String name;
    private String rating;
    private int percentage;
    private String reason;
}
