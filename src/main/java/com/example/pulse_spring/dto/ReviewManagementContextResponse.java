package com.example.pulse_spring.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewManagementContextResponse {
    private String storeName;
    private String storeAddress;
    private ReviewSummaryDto summary;
    @Builder.Default
    private List<ReviewItemDto> reviews = new ArrayList<>();
    private ReviewManagementSettingsDto settings;
    @Builder.Default
    private List<ReviewTemplateDto> templates = new ArrayList<>();
}
