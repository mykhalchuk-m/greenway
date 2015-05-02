package com.mmm.grenway.javafx.service.converter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mmm.greenway.entity.User;
import com.mmm.grenway.javafx.dto.UserDto;

public class UserDtoConverter {
	
	public static ObservableList<UserDto> convertToObservableList(Iterable<User> users) {
		ObservableList<UserDto> data = FXCollections.observableArrayList();
		for (User user : users) {
			data.add(new UserDto(user));
		}
		return data;
	}
	
	
}
