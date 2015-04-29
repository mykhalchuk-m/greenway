package com.mmm.grenway.javafx.controller;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;

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

		tabPane.getTabs().add(adminContentHelper.generateAdminTab());
	}
}
