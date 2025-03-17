package com.example.CoffeeShop.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CoffeeShop.dao.UserDAO;
import com.example.CoffeeShop.dto.UserDTO;
import com.example.CoffeeShop.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDAO dao;
	
	public User join(final UserDTO userDTO) {
		return dao.join(userDTO);
	}

	public User login(String id, String pwd, final PasswordEncoder encoder) {
		return dao.login(id, pwd, encoder);
	}
	
	public User getUser(String id) {
		return dao.getUser(id);
	}

	public boolean checkId(String id) {
		return dao.checkId(id);
	}
	public Page<User> findAll(int page, int size) {
		return dao.findAll(page, size);
	}
	public int getTotalA() {
		return dao.getTotalA();
	}
	
	public boolean userModify(UserDTO dto) {
		return dao.userModify(dto);
	}
	
	public User kakaoLogin(Map<String, Object> userInfo) {
		return dao.kakaoLogin(userInfo);
	}
	

}
