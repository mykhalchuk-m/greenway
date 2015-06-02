package com.mmm.grenway.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.grenway.javafx.controller.helper.AdminContentHelper;

@Component
public class CreateUserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminContentHelper contentHelper;

	@FXML
	private BorderPane createUserPane;

	@FXML
	private void initialize() {
		System.out.println("CreateUserController");
	}

}
