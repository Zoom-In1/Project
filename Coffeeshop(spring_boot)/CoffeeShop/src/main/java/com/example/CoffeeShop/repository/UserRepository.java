package com.example.CoffeeShop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findById (String id);
	
	Page<User> findAll(Pageable pageable); // 관리자 페이지
	
	Optional<User> findByEmail(String email); // 이메일 찾기

}
