package com.mmm.grenway.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.helper.AdminContentHelper;
import com.mmm.grenway.javafx.dto.UserDto;
import com.mmm.grenway.javafx.service.UserDtoService;

@Component
public class UsersController {

	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private CreateUserController createUserController;
	@Autowired
	private ShowUsersController showUsersController;
	@Autowired
	private AdminContentHelper contentHelper;
	@Autowired
	private UserDtoService userService;

	@FXML
	private AnchorPane form;
	@FXML
	private AnchorPane view;

	@FXML
	private void initialize() {
		System.out.println("UsersController");

		form.getChildren().add(contentHelper.createUserForm(this::addUserIntoTable));
		view.getChildren().add(screenConfig.getView(showUsersController, "ShowUsersPane.fxml"));
		
		userService.findAllLocations().forEach(l -> System.out.println("location -> " + l));
	}
	
	public void addUserIntoTable(ActionEvent event, UserDto userDto) {
		UserDto savedUserDto = userService.save(userDto);
		showUsersController.getUsersTable().getItems().add(savedUserDto);
	}
	
}
