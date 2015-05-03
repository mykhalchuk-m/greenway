package com.mmm.grenway.javafx.controller;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.controller.helper.AdminContentHelper;

public class MainController {

	@FXML
	private TabPane tabPane;
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private AdminContentHelper adminContentHelper;

	@FXML
	private void initialize() {
		System.out.println("Main controller");

		UserRole userRole = UserRole.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.iterator().next().getAuthority());

		switch (userRole) {
		case ROLE_ADMIN:
			tabPane.getTabs().add(adminContentHelper.generateAdminTab());
		case ROLE_REGISTRATOR:
			tabPane.getTabs().add(new Tab("Registrator Tab"));
		case ROLE_DOKUMENTOLOH:
			tabPane.getTabs().add(new Tab("Docomentolog Tab"));
		case ROLE_OPERATOR:
			tabPane.getTabs().add(new Tab("Operation Tab"));
		}
	}
}
