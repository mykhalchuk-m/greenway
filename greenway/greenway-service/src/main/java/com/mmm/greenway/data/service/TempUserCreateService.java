package com.mmm.greenway.data.service;

import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;

public class TempUserCreateService {

	public TempUserCreateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		createAdmin(userRepository, passwordEncoder);
//		fillUsersTable(userRepository, passwordEncoder);
//		createOperator(userRepository, passwordEncoder);
//		createDocumentoloh(userRepository, passwordEncoder);
//		createRegistrator(userRepository, passwordEncoder);
//		createInvitator(userRepository, passwordEncoder);
	}

	private void createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User user = new User();
		user.setUserName("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setRole(UserRole.ROLE_ADMIN);
		userRepository.save(user);
	}

	private void createOperator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User user = new User();
		user.setUserName("op");
		user.setPassword(passwordEncoder.encode("op"));
		user.setRole(UserRole.ROLE_OPERATOR);
		userRepository.save(user);
	}
	
	private void createDocumentoloh(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User user = new User();
		user.setUserName("doc");
		user.setPassword(passwordEncoder.encode("doc"));
		user.setRole(UserRole.ROLE_DOKUMENTOLOH);
		userRepository.save(user);
	}
	
	private void createRegistrator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User user = new User();
		user.setUserName("reg");
		user.setPassword(passwordEncoder.encode("reg"));
		user.setRole(UserRole.ROLE_REGISTRATOR);
		userRepository.save(user);
	}
	
	private void createInvitator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User user = new User();
		user.setUserName("inv");
		user.setPassword(passwordEncoder.encode("inv"));
		user.setRole(UserRole.ROLE_INVITATION_DELIVERY);
		userRepository.save(user);
	}

	private void fillUsersTable(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			User user = new User();
			int randomTillFour = random.nextInt(4);
			user.setUserName(StringRandomizer.getRandomStringValue(randomTillFour + 5));
			user.setPassword(passwordEncoder.encode("1111"));
			switch (randomTillFour) {
			case 0:
				user.setRole(UserRole.ROLE_ADMIN);
				break;
			case 1:
				user.setRole(UserRole.ROLE_OPERATOR);
				break;
			case 2:
				user.setRole(UserRole.ROLE_DOKUMENTOLOH);
				break;	
			case 3:
				user.setRole(UserRole.ROLE_REGISTRATOR);
				break;
			}
			userRepository.save(user);
		}
	}
}
