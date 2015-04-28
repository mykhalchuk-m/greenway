package com.mmm.grenway.javafx.controller;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;

public class MainController {
	
	@FXML
	private TabPane tabPane;
	@Autowired
	private ResourceBundle resourceBundle;
	
	@FXML
	private void initialize() {
		System.out.println("Main controller");
		
		
		Tab adminTab = new Tab();
		adminTab.setText(resourceBundle.getString("main.tab.admin.title"));
		tabPane.getTabs().add(adminTab);
	}
}
