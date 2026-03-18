package com.example.pulse_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*
 * [SignupResponse DTO]
 * 
 * 역할:
 * - 회원가입 성공 시 클라이언트에 반환할 응답 데이터를 담는 객체입니다.
 * - 가입 성공 메시지와 함께, 자동 로그인 처리를 위한 JWT 토큰을 포함합니다.
 * 
 * 팀원 참고:
 * - 가입 즉시 로그인이 되는 UX를 위해 accessToken을 반환합니다.
 * - message 필드는 사용자에게 알림을 띄워줄 때 활용할 수 있습니다.
 */
public class SignupResponse {
    private String message;
    private String accessToken;
    private String analysisTaskId;
    @Builder.Default
    private String tokenType = "Bearer";

    public static SignupResponse of(String message, String accessToken, String analysisTaskId) {
        return SignupResponse.builder()
                .message(message)
                .accessToken(accessToken)
                .analysisTaskId(analysisTaskId)
                .build();
    }
}
