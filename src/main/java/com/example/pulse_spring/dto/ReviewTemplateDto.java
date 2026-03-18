package com.example.pulse_spring.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewTemplateDto {
    private Long id;
    private String name;
    private String content;
    private String tone;
    private String length;
    @Builder.Default
    private List<String> category = new ArrayList<>();
    @Builder.Default
    private List<String> tags = new ArrayList<>();
    private String date;
}
