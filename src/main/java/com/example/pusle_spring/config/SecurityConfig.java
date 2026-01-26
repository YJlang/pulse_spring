package com.example.pusle_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * SecurityConfig 클래스는 Spring Security의 주요 보안 설정을 담당합니다.

 * 1. 비밀번호 암호화를 위한 PasswordEncoder 빈을 등록합니다.
 * 2. SecurityFilterChain을 통해 HTTP 보안 정책을 정의합니다.
 *    - 특정 엔드포인트(/signup, /login, swagger 등)은 인증 없이 접근을 허용합니다.
 *    - 그 외 모든 요청은 인증이 필요합니다.
 * 3. csrf 보안 옵션을 비활성화하여 REST API 개발에 적합하도록 구성하였습니다.
 * 
 * 팀원분들은 이 파일을 참고하여 프로젝트의 인증/인가 관련 정책이나,
 * 추가적인 보안 설정이 필요할 경우 이 클래스에 확장하거나 수정하실 수 있습니다.
 */

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}