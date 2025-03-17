package com.example.CoffeeShop.dao;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.CoffeeShop.repository.UserRepository;
import com.example.CoffeeShop.dto.UserDTO;
import com.example.CoffeeShop.entity.User;

@Repository
public class UserDAO {
	@Autowired
	UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User join(final UserDTO userDTO) {
		if (userDTO == null || userDTO.getId() == null) {
			return null; // 유효하지 않은 userDTO일 경우 null 반환
		}
		final String userId = userDTO.getId();
		if (userRepository.existsById(userId)) {
			return null; // 아이디가 이미 존재하면 회원가입 실패
		}

		userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));
		userDTO.setLogtime(new Date()); // 가입 시간 설정
		User user = userDTO.toEntity();  // DTO를 User 엔티티로 변환
		return userRepository.save(user); // User 엔티티 저장 후 반환
	}

	public User login(String id, String pwd, final PasswordEncoder encoder) {
		User user = userRepository.findById(id).orElse(null); // 아이디로 사용자 찾기
		if (user != null && encoder.matches(pwd, user.getPwd())) {
			return user; // 비밀번호가 일치하면 로그인 성공
		}

		return null; // 로그인 실패
	}

	public User getUser(String id) { // 아이디로 사용자 찾기
		return userRepository.findById(id).orElse(null); 
	}

	public boolean checkId(String id) { // 아이디가 존재하는지 체크
		return userRepository.existsById(id);
	}

	public Page<User> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size); // 페이지 요청 생성
		return userRepository.findAll(pageable); // 페이징 처리된 사용자 목록 반환
	}

	public int getTotalA() { // 페이징 처리된 사용자 목록 반환
		return (int) userRepository.count(); 
	}

	// 회원 정보 수정
	public boolean userModify(UserDTO dto) {
		// 1. 기존 데이터 확인
		User user = userRepository.findById(dto.getId()).orElse(null); // 사용자 조회
		boolean result = false;

		if (user != null) {
			// 2. 데이터 수정
			user.setName(dto.getName());
			user.setPwd(passwordEncoder.encode(dto.getPwd())); // 비밀번호 암호화 후 수정
			user.setGender(dto.getGender());
			user.setTel(dto.getTel());
			// 3. 저장
			User user_result = userRepository.save(user);
			if (user_result != null)
				result = true; // 저장 성공 시 true 반환
		}
		return result; // 수정 결과 반환
	}
	
	public User kakaoLogin(Map<String, Object> userInfo) {
		String email = (String) userInfo.get("email");
		// String nickname = (String) userInfo.get("nickname");

		// 카카오 로그인 토큰은 email 과 password 로 만들어줌
		// String username = email;
		// 패스워드 = 카카오 Id + ADMIN TOKEN
		// String password = kakaoId + ADMIN_TOKEN;

		// DB 에 Kakao email과 같은 회원이 있는지 확인
		User kakaoUser = userRepository.findByEmail(email).orElse(null);

		// 있으면 로그인
		if (kakaoUser != null) {
			return kakaoUser;
		} else {
			return null;
		}
		/*
		 * // 없으면 카카오 정보로 회원가입 if (kakaoUser == null) { // 패스워드 인코딩 String
		 * encodedPassword = passwordEncoder.encode(password); // ROLE = 사용자
		 * 
		 * 
		 * kakaoUser = new User(username, encodedPassword, nickname, kakaoId);
		 * userRepository.save(kakaoUser); }
		 */

	}
}
