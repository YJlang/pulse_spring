package com.example.pulse_spring.config;

import com.example.pulse_spring.domain.Category;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * CategoryDeserializer
 * 
 * Category enum을 역직렬화(JSON → Java 객체 변환) 시 커스텀 로직을 적용합니다.
 * 
 * 배경:
 * - 프론트엔드에서는 한글("한식", "중식" 등)로 카테고리를 전송합니다.
 * - 백엔드의 Category enum은 영문 상수(KOREAN, CHINESE 등)로 정의되어 있습니다.
 * - 기본 Jackson 역직렬화는 enum 이름만 인식하므로 "한식" 같은 값을 처리하지 못합니다.
 * 
 * 해결책:
 * - 이 커스텀 Deserializer는 입력값(한글 또는 영문)을 받아
 * 1) 영문 상수명(KOREAN)으로 직접 매칭 시도
 * 2) 실패 시 각 enum의 description("한식")과 비교하여 매칭
 * - 이를 통해 프론트엔드 수정 없이 한글 카테고리를 정상 처리할 수 있습니다.
 * 
 * 사용법:
 * - Category enum에 @JsonDeserialize(using = CategoryDeserializer.class) 어노테이션
 * 추가
 */
public class CategoryDeserializer extends JsonDeserializer<Category> {

    @Override
    public Category deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();

        if (value == null || value.isBlank()) {
            return null;
        }

        // 1. 먼저 영문 enum 이름으로 직접 매칭 시도 (예: "KOREAN", "JAPANESE")
        try {
            return Category.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            // 영문 매칭 실패 시 다음 단계로
        }

        // 2. 한글 description으로 매칭 시도 (예: "한식" → KOREAN)
        for (Category category : Category.values()) {
            if (category.getDescription().equals(value)) {
                return category;
            }
        }

        // 3. 매칭 실패 시 예외 발생
        throw new IllegalArgumentException(
                String.format("'%s'는 유효한 카테고리가 아닙니다. 다음 중 하나를 사용하세요: 한식, 중식, 일식, 양식, 카페/디저트, 주점, 기타", value));
    }
}
