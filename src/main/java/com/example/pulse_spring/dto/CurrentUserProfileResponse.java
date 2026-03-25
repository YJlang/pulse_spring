package com.example.pulse_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserProfileResponse {
    private String email;
    private String ownerName;
    private String shopName;
    private String shopAddress;
}
