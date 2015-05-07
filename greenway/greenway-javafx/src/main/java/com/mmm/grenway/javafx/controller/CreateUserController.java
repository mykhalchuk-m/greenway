package com.mmm.grenway.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
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
		createUserPane.setCenter(contentHelper.createUserForm(this::swithToShowUsersPane));
	}

	public void swithToShowUsersPane(ActionEvent event, User user) {
		System.out.println(user);
		userRepository.save(user);
		System.out.println("in switch to pane action");
		try {
			Scene scene = ((Node) event.getTarget()).getScene();
			TabPane adminTabPane = (TabPane) scene.lookup("#adminTabPane");

			adminTabPane.getSelectionModel().select(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}