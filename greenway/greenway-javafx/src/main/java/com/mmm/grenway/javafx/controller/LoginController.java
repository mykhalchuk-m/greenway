package com.mmm.grenway.javafx.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mmm.grenway.javafx.cfg.ScreenConfig;

public class LoginController {

	@Autowired
	private ScreenConfig screenConfig;

	@Autowired
	private MainController mainController;

	@Autowired
	private AuthenticationManager authenticationManager;

	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	@FXML
	private Label errorMessage;
	@FXML
	private Button login;

	@FXML
	public void initialize() {
		System.out.println("Login controller");
		login.setOnKeyPressed(manageEnterPressed());
		userName.setOnKeyPressed(manageEnterPressed());
		password.setOnKeyPressed(manageEnterPressed());
	}

	@FXML
	private void doLogin(ActionEvent event) {
		System.out.println("do Login");
		String name = userName.getText();
		String pass = password.getText();

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(name, pass);
			Authentication response = authenticationManager.authenticate(request);

			SecurityContextHolder.getContext().setAuthentication(response);
			screenConfig.loadView(mainController, "MainPane.fxml");
		} catch (AuthenticationException e) {
			errorMessage.setText(e.getMessage());
		}
	}

	private EventHandler<KeyEvent> manageEnterPressed() {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					doLogin(null);
				}
			}
		};
	}
}
