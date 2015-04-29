package com.mmm.grenway.javafx.service;

import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.grenway.javafx.dto.UserDto;
import com.mmm.grenway.javafx.service.converter.UserDtoConverter;

@Component
public class UserDtoService {
	@Autowired
	private UserRepository userRepository;
	
	public ObservableList<UserDto> findUsers() {
		return UserDtoConverter.convertToObservableList(userRepository.findAll());
	}
}
