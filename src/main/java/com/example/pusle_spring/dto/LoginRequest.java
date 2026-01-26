package com.example.pusle_spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/*
 * [LoginRequest DTO]
 * 
 * 역할:
 *   - 로그인 요청시 클라이언트가 전달하는 데이터(이메일과 비밀번호)를 담기 위한 Data Transfer Object입니다.
 *   - AuthController의 /login 엔드포인트에서 RequestBody로 사용되며, 
 *     각 필드는 @NotBlank로 유효성 검증이 적용되어 있습니다.
 *   - 팀원분들은 이 클래스를 통해 로그인 시 어떤 정보가 필요하고, 어떤 유효성 체크가 동작하는지 바로 파악할 수 있습니다.
 *   - lombok의 @Getter, @Setter를 통해 getter/setter가 자동 생성됩니다.
 */

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}