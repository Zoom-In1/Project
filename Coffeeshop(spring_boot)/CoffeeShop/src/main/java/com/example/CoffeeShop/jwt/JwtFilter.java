package com.example.CoffeeShop.jwt;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
  JWT 필터: HTTP 요청에서 JWT를 추출하고 인증을 수행하는 필터
  OncePerRequestFilter를 상속받아 요청당 한 번만 실행됨
  OncePerRequestFilter는 중복 실행을 방지하면서 요청에 대해 필터링 작업을 
  한 번만 수행하도록 돕는 추상 클래스입니다.
 */
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    
    private final TokenProvider tokenProvider;

    /*
      필터의 핵심 메서드: HTTP 요청을 처리하고 JWT를 검증하여 인증 정보를 설정
      @param request     클라이언트로부터 온 HTTP 요청
      @param response    서버가 클라이언트로 반환할 HTTP 응답
      @param filterChain 다른 필터를 이어서 실행하는 필터 체인
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // HTTP 요청에서 쿠키를 통해 JWT 추출
        String token = extractJwtFromCookies(request);
        
        // JWT가 존재하고, 유효하면 인증 정보를 설정
        if (token != null && tokenProvider.validateToken(token)) {  // 토큰 유효성 검증
            String username = tokenProvider.validateAndGetUserId(token);  // 사용자 ID 추출
            
            if (username != null) {  // 사용자 ID가 존재하면
                // JWT를 기반으로 Authentication 객체 생성
                Authentication authentication = tokenProvider.getAuthentication(token);
                // SecurityContext에 Authentication 객체를 설정하여 인증 처리
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        // 필터 체인 계속 실행
        filterChain.doFilter(request, response);
    }
    
    /*
      HTTP 요청의 쿠키에서 JWT를 추출하는 메서드
     
      @param request 클라이언트로부터 온 HTTP 요청
      @return JWT 토큰 (없을 경우 null 반환)
     */
    private String extractJwtFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())  // 쿠키 배열을 스트림으로 변환
                .filter(cookie -> "token".equals(cookie.getName()))  // "token" 이름의 쿠키 필터링
                .findFirst()  // 첫 번째 일치하는 쿠키 찾기
                .map(Cookie::getValue)  // 쿠키의 값을 반환
                .orElse(null);  // 일치하는 쿠키가 없으면 null 반환
        }
        return null;
    }
}
