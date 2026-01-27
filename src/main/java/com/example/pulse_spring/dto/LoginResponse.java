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
 * [LoginResponse DTO]
 * 
 * 역할:
 * - 로그인 성공 시 클라이언트에 반환할 응답 데이터를 담는 객체입니다.
 * - JWT 액세스 토큰과 토큰 타입(Bearer)을 포함합니다.
 * 
 * 팀원 참고:
 * - 프론트엔드에서는 accessToken을 헤더에 포함하여 인증된 요청을 보냅니다.
 * - 추후 리프레시 토큰이나 만료 시간 등이 추가될 수 있습니다.
 */
public class LoginResponse {
    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";

    public static LoginResponse of(String accessToken) {
        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
