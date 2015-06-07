package com.mmm.greenway.data.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;

public class InitUserCreateService {

	public InitUserCreateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		createAdmin(userRepository, passwordEncoder);
		createOperator(userRepository, passwordEncoder);
		createDocumentoloh(userRepository, passwordEncoder);
		createRegistrator(userRepository, passwordEncoder);
		createInvitator(userRepository, passwordEncoder);
	}

	private void createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		if (userRepository.findOne("admin") == null) {
			User user = new User();
			user.setUserName("admin");
			user.setPassword(passwordEncoder.encode("admin"));
			user.setRole(UserRole.ROLE_ADMIN);
			user.setLocation("Lviv");
			userRepository.save(user);
		}
	}

	private void createOperator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		if (userRepository.findOne("op") == null) {
			User user = new User();
			user.setUserName("op");
			user.setPassword(passwordEncoder.encode("op"));
			user.setRole(UserRole.ROLE_OPERATOR);
			user.setLocation("Lviv");
			userRepository.save(user);
		}
	}

	private void createDocumentoloh(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		if (userRepository.findOne("doc") == null) {
			User user = new User();
			user.setUserName("doc");
			user.setPassword(passwordEncoder.encode("doc"));
			user.setRole(UserRole.ROLE_DOKUMENTOLOH);
			user.setLocation("Lviv");
			userRepository.save(user);
		}
	}

	private void createRegistrator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		if (userRepository.findOne("reg") == null) {

			User user = new User();
			user.setUserName("reg");
			user.setPassword(passwordEncoder.encode("reg"));
			user.setRole(UserRole.ROLE_REGISTRATOR);
			user.setLocation("Lviv");
			userRepository.save(user);
		}
	}

	private void createInvitator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		if (userRepository.findOne("inv") == null) {
			User user = new User();
			user.setUserName("inv");
			user.setPassword(passwordEncoder.encode("inv"));
			user.setRole(UserRole.ROLE_INVITATION_DELIVERY);
			user.setLocation("Lviv");
			userRepository.save(user);
		}
	}

}
