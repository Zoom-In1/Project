package com.example.CoffeeShop.dto;

import java.util.Date;

import com.example.CoffeeShop.dto.UserDTO;
import com.example.CoffeeShop.entity.User;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter ,ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
	private String gender;
	private String email;
	private String tel;
	@Temporal(TemporalType.DATE)
	private Date logtime;
	@Temporal(TemporalType.DATE)
	private Date age;
	private String role;
	
	public User toEntity() {
		return new User(name, id, pwd, age, gender, email, tel, logtime, role);
		
		
	}
}
