package com.example.CoffeeShop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.CoffeeShop.jwt.JwtFilter;
import com.example.CoffeeShop.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration // 설정 클래스로 인식
@EnableWebSecurity // Spring Security 활성화
@RequiredArgsConstructor // final 필드 생성자 주입
public class SecurityConfig {
	
		private final TokenProvider jwtProvider; // TokenProvider 주입

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			// HTTP 보안 설정
			http.csrf(csrf -> csrf.disable()) // CSRF 비활성화
		        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// 세션 사용 안 함
		        .authorizeHttpRequests(auth -> auth
		            .requestMatchers("/login/**", "/loginForm", "/join", "/joinForm", "/user/checkId", "/index", "/myPage/**", "/menu", "/mail/**", "/confirm/**", "/map"
		            		, "/storage/**").permitAll() // 인증 없이 접근 가능
		            .requestMatchers("/user/logout", "/user/edit", "/search").hasAnyRole("USER", "ADMIN") // USER 또는 ADMIN 권한 필요
		            .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한 필요
		            .anyRequest().authenticated() // 나머지는 인증 필요

		        )
		        .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
		    return http.build(); 
		}

	}

