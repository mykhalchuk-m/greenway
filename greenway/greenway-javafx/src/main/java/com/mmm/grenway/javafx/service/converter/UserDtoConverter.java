package com.mmm.grenway.javafx.service.converter;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.dto.UserDto;

public class UserDtoConverter {

	public static ObservableList<UserDto> convertToObservableList(Iterable<User> users) {
		ObservableList<UserDto> data = FXCollections.observableArrayList();
		for (User user : users) {
			data.add(new UserDto(user));
		}
		return data;
	}

	public static ObservableList<String> getUserRoles() {
		return FXCollections.observableArrayList(Arrays.asList(UserRole.values()).stream().map(role -> role.toString())
				.collect(Collectors.toList()));
	}
	
	public static User toUser(UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName().get());
		user.setPassword(userDto.getPassword().get());
		user.setRole(UserRole.valueOf(userDto.getRoles().get()));
		user.setAccountNonExpired(userDto.getAccountNonExpired().get());
		user.setAccountNonLocked(userDto.getAccountNonLocked().get());
		user.setCredentialsNonExpired(userDto.getCredentialsNonExpired().get());
		user.setEnabled(userDto.getEnabled().get());
		
		return user;
	}
}
