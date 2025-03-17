package com.example.CoffeeShop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.CoffeeShop.entity.User;
import com.example.CoffeeShop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
	// 1. 주어진 id로 DB에서 사용자 정보를 조회합니다.
		return userRepository.findById(id)
		// 2. 사용자 정보가 존재하면 UserDetails 객체로 변환하여 반환합니다.
				.map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
				// 3. 사용자가 존재하지 않으면 예외를 던집니다.
	}
	
	// DB에 해당하는 User 값이 존재한다면 UserDetails 객체로 리턴
	private UserDetails createUserDetails(User user) {
		//System.out.println("role" + user.getRole());
	// 1. User 객체를 UserDetails 객체로 변환하여 반환합니다.
        return User.builder()
        		.id(user.getId())
                .name(user.getName())
                .pwd(user.getPwd())
                .role(user.getRole())
                .build(); // UserDetails 객체 반환
    }
			
	
}
