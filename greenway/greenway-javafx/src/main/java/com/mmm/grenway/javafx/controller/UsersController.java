package com.mmm.grenway.javafx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.helper.AdminContentHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

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
	private UserRepository userRepository;

	@FXML
	private AnchorPane form;
	@FXML
	private AnchorPane view;

	@FXML
	private void initialize() {
		System.out.println("UsersController");

		form.getChildren().add(contentHelper.createUserForm(this::swithToShowUsersPane));
		view.getChildren().add(screenConfig.getView(showUsersController, "ShowUsersPane.fxml"));
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
