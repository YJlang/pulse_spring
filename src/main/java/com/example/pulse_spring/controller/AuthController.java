package com.example.pulse_spring.controller;

import com.example.pulse_spring.dto.LoginRequest;
import com.example.pulse_spring.dto.LoginResponse;
import com.example.pulse_spring.dto.SignupRequest;
import com.example.pulse_spring.dto.SignupResponse;
import com.example.pulse_spring.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * AuthController 클래스
 *
 * 이 컨트롤러는 회원가입 및 로그인 API 엔드포인트를 제공합니다.
 * - /signup : 사용자 또는 가게의 정보를 입력받아 회원가입 처리를 진행하고, 
 *             성공 시 SignupResponse (가입 메시지, 토큰)를 반환합니다.
 *             또한 회원가입 시 AI 분석을 트리거 하는 등 추가 비즈니스 로직을 포함할 수 있습니다.
 * - /login  : 이메일과 비밀번호로 로그인하여, 성공 시 LoginResponse (토큰)를 반환합니다.
 *
 * 예외 상황(회원가입/로그인 실패 등)은 400 Bad Request와 함께 message를 반환합니다.
 * 
 * 팀원분들은 이 파일을 참고하여 인증 및 인가 관련 API를 확장하거나 수정하실 수 있습니다.
 * 주요 인증로직은 AuthService에서 처리합니다.
 * 
 * Swagger 어노테이션이 추가되어 있으므로, API 명세 확인 및 테스트가 가능합니다.
 */

@Tag(name = "Auth", description = "회원가입 및 인증 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입", description = "사용자/가게 정보를 저장하고 AI 분석을 트리거합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
        try {
            SignupResponse response = authService.signup(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("message", e.getMessage()));
        }
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @Operation(summary = "현재 로그인 사용자 프로필", description = "헤더/사이드바에 필요한 사장님 및 매장 정보를 반환합니다.")
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentProfile(Authentication authentication) {
        try {
            return ResponseEntity.ok(authService.getCurrentProfile(authentication.getName()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }
}
