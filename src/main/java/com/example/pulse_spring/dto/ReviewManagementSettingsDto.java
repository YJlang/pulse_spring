package com.example.pulse_spring.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewManagementSettingsDto {
    @Builder.Default
    private String tone = "친근함";
    @Builder.Default
    private String length = "보통";
    @Builder.Default
    private boolean includeThanks = true;
    @Builder.Default
    private boolean includeGreatDay = true;
    @Builder.Default
    private boolean useEmojis = false;
    @Builder.Default
    private boolean photoThanks = true;
    @Builder.Default
    private String brandPreset = "";
    @Builder.Default
    private List<String> brandPresets = new ArrayList<>();
    @Builder.Default
    private String optionalInstruction = "";
    @Builder.Default
    private List<Map<String, Object>> exceptionCases = new ArrayList<>();
}
