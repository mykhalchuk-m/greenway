package com.mmm.grenway.javafx.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.helper.AdminContentHelper;
import com.mmm.grenway.javafx.controller.helper.DocumentolohContentHelper;
import com.mmm.grenway.javafx.controller.helper.InivitationContaentHelper;
import com.mmm.grenway.javafx.controller.helper.OperatorContentHelper;
import com.mmm.grenway.javafx.controller.helper.RegistratorContentHelper;

@Component
public class MainController {

	@FXML
	private TabPane tabPane;
	@FXML
	private MenuBar mainMenuBar;

	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private AdminContentHelper adminContentHelper;
	@Autowired
	private OperatorContentHelper operatorContentHelper;
	@Autowired
	private InivitationContaentHelper inivitationContaentHelper;
	@Autowired
	private DocumentolohContentHelper documentolohContentHelper;
	@Autowired
	private RegistratorContentHelper registratorContentHelper;
	@Autowired
	private LoginController loginController;

	@FXML
	private void initialize() {
		System.out.println("Main controller");

		UserRole userRole = UserRole.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.iterator().next().getAuthority());
		List<Tab> tabs = new ArrayList<Tab>();
		switch (userRole) {
		case ROLE_ADMIN:
			tabs.add(adminContentHelper.generateAdminTab());
		case ROLE_INVITATION_DELIVERY:
			tabs.add(inivitationContaentHelper.genetateInivitationContent());
			if (userRole != UserRole.ROLE_ADMIN) {
				break;
			}
		case ROLE_REGISTRATOR:
			tabs.add(registratorContentHelper.genetateRegistratorContent());
		case ROLE_DOKUMENTOLOH:
			tabs.add(documentolohContentHelper.genetateDocumentolohContent());
		case ROLE_OPERATOR:
			tabs.add(operatorContentHelper.generateOperatorTab());
		}
		Collections.reverse(tabs);
		tabPane.getTabs().addAll(tabs);
	}

	@FXML
	private void onLogOut() {
		SecurityContextHolder.clearContext();
		screenConfig.loadView(loginController, "LoginPane.fxml");
	}

	@FXML
	private void onExit(ActionEvent e) {
		Platform.exit();
	}

	@FXML
	private void onAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText(null);
		alert.setContentText("This is the most anazinest program you have ever seen!");

		alert.showAndWait();
	}
}
