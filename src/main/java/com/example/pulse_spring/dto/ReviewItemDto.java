package com.example.pulse_spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewItemDto {
    private String id;
    private String author;
    private Double rating;
    private String date;
    private String content;
    private String rawText;
    private boolean hasPhoto;
    private String source;
    private String sourceLabel;
}
