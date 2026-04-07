package com.example.pulse_spring.controller;

import com.example.pulse_spring.dto.LoginRequest;
import com.example.pulse_spring.dto.LoginResponse;
import com.example.pulse_spring.dto.SignupRequest;
import com.example.pulse_spring.dto.SignupResponse;
import com.example.pulse_spring.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void signupSuccess() {
        SignupRequest request = new SignupRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        request.setPasswordConfirm("password123");

        SignupResponse expectedResponse = SignupResponse.of(
                "가입이 완료되었습니다.",
                "jwt-token",
                "analysis-task-id"
        );

        when(authService.signup(request)).thenReturn(expectedResponse);

        ResponseEntity<?> result = authController.signup(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccess() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        LoginResponse expectedResponse = LoginResponse.of("jwt-token");

        when(authService.login(request)).thenReturn(expectedResponse);

        ResponseEntity<?> result = authController.login(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(expectedResponse);
    }
}
