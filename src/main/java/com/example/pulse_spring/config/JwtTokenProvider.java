package com.example.pulse_spring.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/*
 * JWT 토큰을 생성하고 관리하는 역할의 클래스입니다.
 * 
 * - JwtTokenProvider는 인증 및 인가를 위해 JWT(Json Web Token)를 발급합니다.
 * - 외부 설정 값(jwt.secret, jwt.expiration)을 불러와, HS256 알고리즘을 사용해 토큰을 생성합니다.
 * - 토큰에는 발급 시간(issuedAt), 만료 시간(expiration), 사용자 이메일(subject) 정보가 담깁니다.
 *
 * 팀원분들은 인증 및 인가가 필요한 서비스에서 토큰 생성이 필요할 때 이 클래스를 활용하시면 됩니다.
 */

@Component
public class JwtTokenProvider {
    private final Key key;
    private final long expiration;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiration = expiration;
    }

    public String createToken(String email) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}