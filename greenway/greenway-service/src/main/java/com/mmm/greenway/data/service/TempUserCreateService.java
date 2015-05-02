package com.mmm.greenway.data.service;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;

public class TempUserCreateService {

	public TempUserCreateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		createAdmin(userRepository, passwordEncoder);
	}
	
	private void createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User user = new User();
		user.setUserName("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setRoles(Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_DOKUMENTOLOH, UserRole.ROLE_REGISTRATOR));
		userRepository.save(user);
	}
}
