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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.helper.AdminContentHelper;
import com.mmm.grenway.javafx.controller.helper.DocumentolohContentHelper;
import com.mmm.grenway.javafx.controller.helper.InivitationContentHelper;
import com.mmm.grenway.javafx.controller.helper.OperatorContentHelper;
import com.mmm.grenway.javafx.controller.helper.RegistratorContentHelper;

@Component
public class MainController {

	@FXML
	private AnchorPane mainContent;
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
	private InivitationContentHelper inivitationContaentHelper;
	@Autowired
	private DocumentolohContentHelper documentolohContentHelper;
	@Autowired
	private RegistratorContentHelper registratorContentHelper;
	@Autowired
	private LoginController loginController;
	private ProgressIndicator progressIndicator = new ProgressIndicator();

	@FXML
	private void initialize() {
		System.out.println("Main controller");
		progressIndicator.setVisible(false);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(progressIndicator);
		AnchorPane.setLeftAnchor(borderPane, 0.0);
		AnchorPane.setTopAnchor(borderPane, 50.0);
		AnchorPane.setRightAnchor(borderPane, 0.0);
		mainContent.getChildren().add(borderPane);

		UserRole userRole = UserRole.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.iterator().next().getAuthority());
		List<Tab> tabs = new ArrayList<Tab>();
		switch (userRole) {
		case ROLE_ADMIN:
			tabs.add(adminContentHelper.generateAdminTab());
			tabs.add(inivitationContaentHelper.genetateInivitationTab(progressIndicator));
			tabs.add(registratorContentHelper.genetateRegistratorTab(progressIndicator));
			tabs.add(documentolohContentHelper.genetateDocumentolohTab(progressIndicator));
			tabs.add(operatorContentHelper.generateOperatorTab(progressIndicator));
			Collections.reverse(tabs);
			TabPane tabPane = new TabPane();
			tabPane.getTabs().addAll(tabs);
			AnchorPane.setTopAnchor(tabPane, 0.0);
			AnchorPane.setLeftAnchor(tabPane, 0.0);
			AnchorPane.setBottomAnchor(tabPane, 0.0);
			AnchorPane.setRightAnchor(tabPane, 0.0);
			mainContent.getChildren().add(tabPane);
			break;
		case ROLE_INVITATION_DELIVERY:
			mainContent.getChildren().add(inivitationContaentHelper.genetateInivitationContent(progressIndicator));
			break;
		case ROLE_REGISTRATOR:
			mainContent.getChildren().add(registratorContentHelper.genetateRegistratorContent(progressIndicator));
			break;
		case ROLE_DOKUMENTOLOH:
			mainContent.getChildren().add(documentolohContentHelper.genetateDocumentolohContent(progressIndicator));
			break;
		case ROLE_OPERATOR:
			mainContent.getChildren().add(operatorContentHelper.generateOperatorContent(progressIndicator));
		}

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
		alert.setContentText(resourceBundle.getString("menu.about"));

		alert.showAndWait();
	}
}
