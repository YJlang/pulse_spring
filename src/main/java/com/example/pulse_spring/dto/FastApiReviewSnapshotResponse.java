package com.example.pulse_spring.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FastApiReviewSnapshotResponse {
    private String storeName;
    private String address;
    private int totalReviews;
    @Builder.Default
    private Map<String, Integer> sourceCounts = new HashMap<>();
    private String lastCrawledAt;
    @Builder.Default
    private List<ReviewItemDto> reviews = new ArrayList<>();
}
