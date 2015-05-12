package com.mmm.grenway.javafx.controller;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;

@Component
// @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LoginController {
	private static final String EMPTY_STRING = "";

	@Autowired
	private ScreenConfig screenConfig;

	@Autowired
	private ResourceBundle resourceBundle;

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
	private ContextMenu userNameAlert;
	private ContextMenu passwordAlert;

	@FXML
	public void initialize() {
		userNameAlert = new ContextMenu(new MenuItem(
				resourceBundle.getString("login.textFiled.userName.alert")));
		passwordAlert = new ContextMenu(new MenuItem(
				resourceBundle.getString("login.textFiled.password.alert")));
		
		userNameAlert.setAutoHide(false);
		passwordAlert.setAutoHide(false);

		login.setOnAction(event -> doLogin(event));
		userName.setOnKeyPressed(manageEnterPressed());
		password.setOnKeyPressed(manageEnterPressed());
		login.setOnKeyPressed(manageEnterPressed());
	}

	@FXML
	private void doLogin(ActionEvent event) {
		if (isValid()) {
			String name = userName.getText();
			String pass = password.getText();

			try {
				Authentication request = new UsernamePasswordAuthenticationToken(name, pass);
				Authentication response = authenticationManager.authenticate(request);

				SecurityContextHolder.getContext().setAuthentication(response);
				screenConfig.loadView(mainController, "MainPane.fxml");
			} catch (AuthenticationException e) {
				errorMessage.setText(resourceBundle.getString("login.errorMessage.invelidCredetional"));
			}
		}
	}

	private boolean isValid() {
		boolean isValid = true;

		if (!userNameAlert.isShowing() && EMPTY_STRING.equals(userName.getText())) {
			isValid = false;
			userNameAlert.show(userName, Side.RIGHT, 10, 0);
		}
		if (!passwordAlert.isShowing() && EMPTY_STRING.equals(password.getText())) {
			isValid = false;
			passwordAlert.show(password, Side.RIGHT, 10, 0);
		}
		if (EMPTY_STRING.equals(password.getText()) || EMPTY_STRING.equals(userName.getText())) {
			isValid = false;
		}

		userName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					userNameAlert.hide();
				}
			}
		});

		password.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					passwordAlert.hide();
				}
			}
		});

		userName.getScene().addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userNameAlert.hide();
				passwordAlert.hide();
			}
		});

		return isValid;
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
