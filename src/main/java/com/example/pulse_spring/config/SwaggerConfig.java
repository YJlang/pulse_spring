package com.example.pulse_spring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * SwaggerConfig 클래스는 프로젝트의 Swagger(OpenAPI 3) 문서 자동화 및 보안 설정을 담당합니다.
 *
 * - 팀원분들은 이 설정을 통해 API 엔드포인트 문서화(swagger-ui)와 JWT 인증 테스트가 가능합니다.
 * - Swagger UI에서 "Authorize" 버튼을 통해 JWT 토큰을 입력하고, 인증이 필요한 API를 직접 테스트할 수 있습니다.
 * - API 문서화 정보(제목, 버전, 설명)와 Bearer 토큰 방식 보안 스키마를 한번에 등록합니다.
 * 
 * 참고: springdoc-openapi 라이브러리가 적용되어 있어야 정상적으로 동작합니다.
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                            .title("PULSE API")
                            .version("1.3")
                            .description("회원가입/로그인 및 분석 요청 API")
                )
                .addSecurityItem(new SecurityRequirement().addList("Bearer Auth"))
                .components(
                        new Components()
                            .addSecuritySchemes("Bearer Auth",
                                    new SecurityScheme()
                                            .name("Bearer Auth")
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")
                            )
                );
    }
}