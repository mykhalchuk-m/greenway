package com.mmm.grenway.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	@FXML
	private Label errorMessage;
	@FXML
	private Button login;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Login controller");
	}
	
	@FXML
	private void doLogin(ActionEvent event) {
		System.out.println("do Login");
	}
}
