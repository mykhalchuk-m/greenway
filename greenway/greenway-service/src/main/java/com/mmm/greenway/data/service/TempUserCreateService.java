package com.mmm.greenway.data.service;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;

public class TempUserCreateService {

	public TempUserCreateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		createAdmin(userRepository, passwordEncoder);
		fillUsersTable(userRepository, passwordEncoder);
	}

	private void createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User user = new User();
		user.setUserName("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setRoles(Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_DOKUMENTOLOH, UserRole.ROLE_REGISTRATOR));
		userRepository.save(user);
	}

	private void fillUsersTable(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setUserName(StringRandomizer.getRandomStringValue(i % 4 + 5));
			user.setPassword(passwordEncoder.encode("1111"));
			user.setRoles(Arrays.asList(UserRole.ROLE_ADMIN));
			userRepository.save(user);
		}
	}
}
