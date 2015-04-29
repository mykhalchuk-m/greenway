package com.mmm.grenway.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class CreateUserController {

	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	@FXML
	private TextField confirmPassword;
	@FXML
	private ComboBox<String> roles;
	@FXML
	private Button createUser;

	@FXML
	private void initialize() {
		System.out.println("CreateUserController");
		createUser.setOnAction(event -> doCreateUser(event));
	}

	@FXML
	private void doCreateUser(ActionEvent event) {
		System.out.println("doCrateUser");
		try {
			Scene scene = ((Node) event.getTarget()).getScene();
			TabPane adminTabPane = (TabPane) scene.lookup("#adminTabPane");
			System.out.println(adminTabPane.getTabs().size());
			
			adminTabPane.getSelectionModel().select(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
