package com.mmm.grenway.javafx.service;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.dto.UserDto;
import com.mmm.grenway.javafx.service.converter.UserDtoConverter;

@Component
public class UserDtoService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public StringProperty getCurentUserName() {
		return new SimpleStringProperty(getCurrentUserDetails().getUsername());
	}

	public UserDetails getCurrentUserDetails() {
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public ObservableList<UserDto> findUsers() {
		return UserDtoConverter.convertToObservableList(userRepository.findAll());
	}

	public ObservableList<UserDto> findUsersStartsWithName(String userName) {
		return UserDtoConverter
				.convertToObservableList(userRepository.findByUserNameStartsWith(userName.toUpperCase()));
	}

	public ObservableList<UserDto> findUsersWithRoles(UserRole userRole) {
		return UserDtoConverter.convertToObservableList(userRepository.findUsersWithRole(userRole));
	}

	public ObservableList<UserDto> findUsers(String userName, UserRole userRole) {
		System.out.println(userName + " " + userRole);
		if ((userName == null || userName.isEmpty()) && userRole != null) {
			return findUsersWithRoles(userRole);
		} else if (userRole == null && userName != null && !userName.isEmpty()) {
			return findUsersStartsWithName(userName);
		} else if (userRole != null && userName != null && !userName.isEmpty()) {
			return UserDtoConverter.convertToObservableList(userRepository.findUsersWithRoleAndNameStartsWith(userRole,
					userName));
		} else {
			return findUsers();
		}
	}

	public UserDto save(UserDto userDto) {
		User user = userRepository.save(UserDtoConverter.toUser(userDto, passwordEncoder));
		return new UserDto(user);
	}

	public void remove(UserDto userDto) {
		userRepository.delete(userDto.getUserName().get());
	}

	public ObservableList<String> findAllLocations() {
		ObservableList<String> result = FXCollections.observableArrayList();
		userRepository.findAllLocations().forEach(l -> result.add(l));
		return result;
	}

	public UserDto findUser(String userName) {
		return new UserDto(userRepository.findOne(userName));
	}
}
