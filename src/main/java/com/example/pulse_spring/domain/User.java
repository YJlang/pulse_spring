package com.example.pulse_spring.domain;

import jakarta.persistence.*;
import lombok.*;

/*
 * User 엔티티 클래스
 * 
 * [역할 및 설명]
 * - 이 클래스는 "회원(사용자)" 정보를 데이터베이스에 저장하는 JPA 엔티티입니다.
 * - 회원의 주요 정보(이메일, 비밀번호, 이름, 연락처, 개인정보 동의 여부 등)를 관리합니다.
 * - 엔티티 어노테이션(@Entity, @Table)을 통해 DB 테이블(users)과 매핑되어 영속화됩니다.
 * - Lombok을 이용하여 코드의 간결성을 높이고, 생성자/빌더 패턴 및 getter 메소드를 자동생성합니다.
 * 
 * [팀원 참고사항]
 * - 회원가입, 로그인, 인증 등 유저 도메인 관련 기능에서 기본적으로 사용되는 핵심 엔티티입니다.
 * - email 필드는 유일하게(unique) 관리되어야 하며, 필요한 경우 협업 시 추가 필드를 상의하여 보완하면 됩니다.
 * - 관련 Service/Repository 계층에서 활용하거나, 가게(Shop)와의 관계 매핑(1:1 등)에서 참조됩니다.
 */

@Entity
@Table(name = "users") // 'user'는 DB 예약어일 가능성이 높아 테이블명을 users로 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 지연 로딩을 위한 기본 생성자 (외부 접근 제한)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email; // 로그인 아이디로 사용 (Unique)

    @Column(nullable = false)
    private String password; // BCrypt 암호화 저장

    @Column(nullable = false)
    private String name; // 사용자명

    private String phone; // 연락처

    private boolean isPrivacyAgreed; // 개인정보 수집 동의 여부
}