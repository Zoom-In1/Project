package com.example.CoffeeShop.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "userable")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
	private String name;
	@Id
	private String id; // 사용자의 ID(기본 키로 사용)
	private String pwd;
	@Temporal(TemporalType.DATE)
	private Date age; // 사용자의 생년월일
	private String gender;
	private String email;
	private String tel;
	@Temporal(TemporalType.DATE)
	private Date logtime; // 사용자가 로그인한 시간
	private String role; // 사용자의 역할 (예: ADMIN, USER)
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		// 권한을 저장할 Set을 생성합니다. Set은 중복을 허용하지 않으므로, 동일한 권한이 여러 번 추가되지 않습니다.
		
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));
        // 사용자 역할(role)을 기반으로 "ROLE_" 접두사를 붙여 권한을 생성하고, 이를 authorities Set에 추가합니다.
        // 예: role 값이 "ADMIN"이라면, "ROLE_ADMIN" 권한이 추가됩니다.
        
        return authorities;
        // 생성된 권한 리스트를 반환합니다.
	}	
	
	@Override
	public String getPassword() {
		
		return pwd;
	}
	@Override
	public String getUsername() {
		
		return id;
	}
	
	

}
