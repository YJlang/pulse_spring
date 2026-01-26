package com.example.pusle_spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Category enum 클래스
 *
 * 역할:
 *  - 가게의 분류(한식, 일식, 중국집, 양식, 카페/디저트, 주점, 기타 등)를 구분하기 위한 열거형 Enum입니다.
 *  - 각 카테고리별 한글 설명(예: "한식", "일식" 등)을 함께 관리합니다.
 *  - 회원가입, 가게 등록, 검색 등에서 카테고리 분류를 일관성 있게 처리할 수 있도록 사용합니다.
 *
 * 팀원 참고사항:
 *  - 새로운 음식 카테고리 추가 시 여기 Enum에 값을 추가하면 됩니다.
 *  - description 필드를 이용해 뷰/응답에서 한글 카테고리명을 손쉽게 불러올 수 있습니다.
 */

@Getter
@RequiredArgsConstructor
public enum Category {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    CAFE_DESSERT("카페/디저트"),
    BAR("주점"),
    ETC("기타");

    private final String description;
}