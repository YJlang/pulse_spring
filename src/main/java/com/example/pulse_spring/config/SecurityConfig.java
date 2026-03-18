package com.example.pulse_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

import java.util.List;

/*
 * SecurityConfig 클래스는 Spring Security의 주요 보안 설정을 담당합니다.
 * 
 * 1. 주요 어노테이션
 *    - @Configuration: 설정 파일임을 명시합니다.
 *    - @EnableWebSecurity: Spring Security 설정을 활성화하며, 웹 보안 지원을 제공합니다.
 * 
 * 2. SecurityFilterChain 설정
 *    - CORS 활성화: React 프론트엔드(localhost:5173)에서의 요청을 허용합니다.
 *    - CSRF 비활성화: REST API 서버이므로 CSRF 보호를 끕니다.
 *    - Authorization (인가) 설정:
 *      * DispatcherType.FORWARD, ERROR: 내부 포워딩 및 에러 페이지 접근을 허용합니다 (403 방지).
 *      * /api/auth/**, /swagger-ui/**: 인증 없이 접근 가능한 공개 엔드포인트입니다.
 *      * 그 외 모든 요청은 인증(Authenticated)이 필요합니다.
 * 
 * 3. PasswordEncoder
 *    - BCryptPasswordEncoder를 사용하여 비밀번호를 안전하게 해싱합니다.
 * 
 * 팀원 참고:
 *   - 인증이 필요한 새 API 추가 시 anyRequest().authenticated() 정책에 의해 자동 보호됩니다.
 *   - 공개 API가 필요한 경우 requestMatchers에 경로를 추가하세요.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
