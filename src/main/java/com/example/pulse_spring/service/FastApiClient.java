package com.example.pulse_spring.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/*
 * [FastApiClient 역할]
 * ------------------------------------------------------------
 * - 이 클래스는 외부 Python(FastAPI) 서버로 비동기 분석 요청을 보낼 때 사용되는 서비스입니다.
 * - 회원 가입 시점 등의 비즈니스 로직에서 가게 정보를 FastAPI로 전달하여
 *   데이터 분석, 크롤링, AI 예측 등 백엔드 파이프라인 입력을 트리거합니다.
 * - @Async 어노테이션을 통해 실제 API 요청은 메인 쓰레드를 블로킹하지 않고 비동기로 처리됩니다.
 * - 팀원 분들은 'sendAnalysisRequest' 메서드를 활용해 파라미터만 넘겨주면 분석 요청을 쉽게 날릴 수 있습니다.
 * - 추후 FastAPI 서버 주소(FASTAPI_URL) 지정 및 요청 스펙 변경 시 이 부분만 수정하면 됩니다.
 * - 오류 발생 시 표준 에러 로그로 실패 원인을 확인할 수 있습니다.
 */

@Service
public class FastApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String FASTAPI_URL = ""; // TODO: 연동할 Python FastAPI 서버의 실제 주소로 설정하세요.

    /**
     * 외부 FastAPI 서버에 가게 분석 요청을 전송합니다. (비동기 처리)
     *
     * @param shopId    가게 ID
     * @param shopName  가게 이름
     * @param address   가게 주소
     * @param keyword   분석에 사용할 검색 키워드
     */
    @Async
    public void sendAnalysisRequest(Long shopId, String shopName, String address, String keyword) {
        try {
            Map<String, Object> request = new HashMap<>();
            request.put("shopId", shopId);
            request.put("shopName", shopName);
            request.put("address", address);
            request.put("searchKeyword", keyword);

            restTemplate.postForObject(FASTAPI_URL, request, String.class);
            System.out.println("✅ [Async] FastAPI 분석 요청 전송 완료: " + shopName);
        } catch (Exception e) {
            System.err.println("❌ [Async] FastAPI 연동 실패: " + e.getMessage());
        }
    }
}