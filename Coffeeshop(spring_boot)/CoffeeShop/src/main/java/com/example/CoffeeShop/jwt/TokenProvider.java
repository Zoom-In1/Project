package com.example.CoffeeShop.jwt;

import java.util.Arrays;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.CoffeeShop.entity.User;
import com.example.CoffeeShop.service.JwtUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {
	private final JwtUserDetailsService jwtUserDetailsService;
	
	// secretKey는 application.properties
	@Value("${jwt.secret}")
	private String secretKey;
	
	// 토큰 유효 시간 (초 단위)
	@Value("${jwt.token-validity-in-seconds}")
	private long tokenValidityInSeconds;
		
	private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	

	// JWT 토큰 생성 메서드
    // 사용자 정보를 기반으로 JWT 토큰을 생성하여 반환
    public String createToken(User user) {
        Date now = new Date();
        // 토큰 만료 시간 설정
        Date expiration = new Date(now.getTime() + tokenValidityInSeconds * 1000);

        // JWT 생성 
        return Jwts.builder()
                .setSubject(user.getId())  // 사용자 ID를 subject로 설정
                .setIssuedAt(now)  // 토큰 발급 시간 설정
                .setExpiration(expiration)  // 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)  // 서명
                .compact();  // 최종적으로 compact 형태로 반환
    }
    
    // JWT 토큰에서 Claims 객체를 추출하는 메서드
    // Claims는 토큰의 내용을 담고 있는 객체로, 사용자 정보 등 다양한 데이터가 포함됨
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  // 서명에 사용할 키 설정
                .build()
                .parseClaimsJws(token)  // JWT 파싱하여 Claims 객체 추출
                .getBody();  // Claims 객체 반환
    }
    
    // 토큰을 검증하고 사용자 ID를 반환하는 메서드
    //사용자 ID 추출: 토큰에서 subject를 가져오는 일
    public String validateAndGetUserId(String token) {
        // Claims에서 subject(사용자 ID) 추출
        return getClaims(token).getSubject();
    }

    // JWT 토큰 유효성 검증 메서드 
    // 유효성 검증: 토큰이 유효한지 확인 (true/false 반환)
    public boolean validateToken(String token) {
        try {
            // 토큰이 유효하면 Claims 객체를 추출
            getClaims(token);
            return true;  // 유효한 토큰이면 true 반환
        } catch (Exception e) {
            log.warn("토큰 인증 실패: {}", e.getMessage());  // 예외 발생 시 경고 로그 출력
        }
        return false;  // 예외가 발생하면 false 반환
    }

    // HTTP 요청에서 쿠키를 통해 JWT 토큰을 추출하는 메서드
    public String resolveTokenFromCookie(HttpServletRequest request) {
        // 쿠키가 없으면 null 반환
        if (request.getCookies() == null) return null;

        // 쿠키에서 "token" 이름을 가진 쿠키를 찾아 그 값을 반환
        return Arrays.stream(request.getCookies())
                     .filter(cookie -> "token".equals(cookie.getName()))
                     .findFirst()  // 첫 번째 해당 쿠키 찾기
                     .map(Cookie::getValue)  // 쿠키 값 반환
                     .orElse(null);  // "token" 쿠키가 없으면 null 반환
    }

    // JWT 토큰을 기반으로 Authentication 객체를 생성하는 메서드
    // Spring Security에서 Authentication은 사용자의 인증 정보를 담고 있음
    public Authentication getAuthentication(String token) {
        // JWT 토큰에서 사용자 ID를 추출
        String userId = validateAndGetUserId(token);
        // JwtUserDetailsService를 통해 사용자 정보를 로드
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userId);
        // 인증 객체 반환
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}




