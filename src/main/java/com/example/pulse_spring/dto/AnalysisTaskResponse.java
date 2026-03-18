package com.example.pulse_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalysisTaskResponse {
    @JsonProperty("task_id")
    private String taskId;

    private String status;
    private String message;
}
