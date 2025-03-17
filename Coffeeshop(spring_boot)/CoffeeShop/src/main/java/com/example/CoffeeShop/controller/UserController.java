package com.example.CoffeeShop.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShop.dto.UserDTO;
import com.example.CoffeeShop.entity.User;
import com.example.CoffeeShop.jwt.KakaoAPI;
import com.example.CoffeeShop.jwt.TokenProvider;
import com.example.CoffeeShop.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 로그인, 회원가입, 마이페이지 API
// @CrossOrigin(origins = "http://localhost:3000") // React 앱의 주소
@RestController
public class UserController {
	
	@Autowired
	UserService service;
	@Autowired
	private TokenProvider tokenProvider;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private KakaoAPI kakaoApi;
	
	
	// 로그인 (폼, 기능)
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("req", "/user/loginForm");
		return "/index";
	}
	
	@ResponseBody
	@PostMapping(value = "/login")
	public String login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
		// UserDTO에서 전달된 ID와 비밀번호를 사용하여 로그인 검증
		User user =  service.login(userDTO.getId(), userDTO.getPwd(), passwordEncoder);
		
		if(user != null) {
			// 토큰 생성
			final String token = tokenProvider.createToken(user);
			// JWT 토큰을 HttpOnly 쿠키에 저장
			ResponseCookie cookie = ResponseCookie.from("token", token).httpOnly(true) 
					.secure(true) // Https에서만 전송
					.path("/").maxAge(24*60*60) // 쿠키 유효기간 설정(1일)
					.sameSite("Strict") // SameSite 속성 설정
					.build();
			response.setHeader("Set-Cookie", cookie.toString());

			System.out.println("login : " + token);
			return "Login Success";

		} else {
			// UserResponseDTO responseDTO = UserResponseDTO.builder().error("Login
			// failed.").build();
			return "Login Failed";
		}

	}
	// 로그아웃 (기능)
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// 요청에서 쿠키를 가져와서 "token" 이름의 쿠키를 찾음
	    Optional<Cookie> tokenCookie = Arrays.stream(request.getCookies()) // 모든 쿠키에서 "token" 쿠키를 찾음
	            .filter(cookie -> cookie.getName().equals("token")).findFirst();

	    if (tokenCookie.isPresent()) { // "token" 쿠키가 존재하면
	        Cookie cookie = tokenCookie.get();
	        cookie.setValue(""); // 쿠키 값을 빈 문자열로 설정하여 무효화
	        cookie.setMaxAge(0); // 쿠키의 유효 시간을 0으로 설정하여 즉시 만료
	        cookie.setPath("/"); // 쿠키의 경로 설정 (전체 경로에서 쿠키 유효)
	        response.addCookie(cookie); // 응답에 쿠키를 추가하여 클라이언트에서 삭제하도록 유도
	    }
	    
	    return "/user/logout"; // 로그아웃 후 사용자에게 보여줄 페이지 반환 (뷰 페이지)
	}
	
	// 회원가입 (기능, 폼)
	@GetMapping("/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("req", "/user/joinForm");
		return "/index";
	}
	
	@PostMapping("/join")
	public String join(UserDTO userDTO, Model model, HttpServletRequest request) {
	
		return "/user/join";
	}
	// 아이디 중복 체크 (기능)
	@GetMapping("/user/checkId")
	public String checkId(Model model, HttpServletRequest request) {
		// 1. 데이터 처리
		String id = request.getParameter("id");
		// db
		boolean result = service.checkId(id);

		// 2. 데이터 공유
		model.addAttribute("result", result);
		model.addAttribute("id", id);

		// 3. view 파일 선택
		return "/user/checkId";
	}
	
	// 회원정보 변경(폼, 기능)
	@GetMapping("/user/editForm")
	public String editForm(Model model, HttpServletRequest request) {
		String token = tokenProvider.resolveTokenFromCookie(request); // 쿠키에서 token 가져오기
		String userId = tokenProvider.validateAndGetUserId(token); // token이 유효한지 확인하고 userId 가져오기
		if (userId != null) {
			User user = service.getUser(userId);
			System.out.println(user);
			model.addAttribute("user", user);
			model.addAttribute("req", "/user/editForm");
			return "/index";
		} else {
			return "/index";
		}

	}
	
	@PostMapping("/edit")
	public String edit(UserDTO userDTO, Model model, HttpServletRequest request) {

		boolean result = service.userModify(userDTO);
		model.addAttribute("result", result);
		return "/user/edit";
	}
	
	// 카카오 로그인
	@GetMapping(value = "/login/oauth2/code/kakao")
	public String kakaoLogin(@RequestParam(value = "code") String code, HttpServletResponse response, Model model) {
	    // 카카오에서 보낸 인가 코드를 통해 액세스 토큰을 받아옴
	    String accessToken = kakaoApi.getAccessToken(code);

	    // 받은 액세스 토큰을 사용해 카카오 사용자 정보를 가져옴
	    Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
	    // 사용자 정보를 통해 kakaoUser 객체를 생성 (로그인 또는 회원가입 처리)
	    User kakaoUser = service.kakaoLogin(userInfo);
	    String email = (String) userInfo.get("email");  // 사용자 이메일
	    String nickname = (String) userInfo.get("nickname");  // 사용자 닉네임

	    // 카카오 계정으로 이미 등록된 정보가 있으면 로그인 처리
	    if (kakaoUser != null) {
	        // 로그인 성공 시, JWT 토큰을 생성하여 사용자에게 전달
	        final String token = tokenProvider.createToken(kakaoUser);
	        // 생성된 토큰을 HttpOnly 쿠키로 설정하여 클라이언트에게 전달
	        ResponseCookie cookie = ResponseCookie.from("token", token).httpOnly(true) // 자바스크립트에서 접근 불가
	                .secure(true) // HTTPS 연결에서만 전송
	                .path("/") // 쿠키가 적용될 경로
	                .maxAge(24 * 60 * 60) // 쿠키 유효기간을 1일로 설정
	                .sameSite("Strict") // SameSite 속성 설정
	                .build();

	        // 쿠키를 응답 헤더에 추가하여 클라이언트에게 전달
	        response.setHeader("Set-Cookie", cookie.toString());

	        System.out.println("kakao login: " + token);
	        return "redirect:/index";  // 로그인 후 리디렉션
	    } else {
	        // 카카오 이메일로 등록된 사용자가 없으면 회원가입 페이지로 이동
	        System.out.println("실행");
	        model.addAttribute("req", "/user/joinForm");
	        model.addAttribute("email", email);  // 이메일을 모델에 추가
	        model.addAttribute("name", nickname);  // 닉네임을 모델에 추가
	        return "/index";  // 회원가입 폼으로 이동
	    }

	    /*
	     * 디버깅을 위한 주석 처리된 코드
	     * System.out.println("email = " + email);
	     * System.out.println("nickname = " + nickname);
	     * System.out.println("accessToken = " + accessToken);
	     */
	}
	
	// 마이페이지
}
