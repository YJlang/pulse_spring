package com.example.pulse_spring.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerateReviewRepliesResponse {
    @Builder.Default
    private List<GeneratedReviewReplyDto> replies = new ArrayList<>();
}
