package com.mmm.grenway.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.service.converter.UserDtoConverter;

@Component
public class EditUserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@FXML
	private TextField userNameFiled;
	@FXML
	private PasswordField passwordField;
	@FXML
	private ComboBox<String> roleField;
	@FXML
	private CheckBox accountNonExpiredField;
	@FXML
	private CheckBox accountNonLockedField;
	@FXML
	private CheckBox credentialsNonExpiredField;
	@FXML
	private CheckBox enabledField;

	private User user;
	private boolean isUserChanged;
	private boolean isUserRemoved;

	@FXML
	private void initialize() {
		System.out.println("EditUserController");
		if (user != null) {
			userNameFiled.setText(user.getUserName());
			userNameFiled.setEditable(false);
			passwordField.setText(user.getPassword());
			passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
				user.setPassword(passwordEncoder.encode(newValue));
				isUserChanged = true;
			});
			roleField.setItems(UserDtoConverter.getUserRoles());
			roleField.setValue(user.getRole().name());
			roleField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				user.setRole(UserRole.valueOf(newValue));
				isUserChanged = true;
			});
			accountNonExpiredField.setSelected(user.getAccountNonExpired());
			accountNonExpiredField.selectedProperty().addListener((observable, oldValue, newValue) -> {
				user.setAccountNonExpired(newValue);
				isUserChanged = true;
			});
			accountNonLockedField.setSelected(user.getAccountNonLocked());
			accountNonLockedField.selectedProperty().addListener((observable, oldValue, newValue) -> {
				user.setAccountNonLocked(newValue);
				isUserChanged = true;
			});
			credentialsNonExpiredField.setSelected(user.getCredentialsNonExpired());
			credentialsNonExpiredField.selectedProperty().addListener((observable, oldValue, newValue) -> {
				user.setCredentialsNonExpired(newValue);
				isUserChanged = true;
			});
			enabledField.setSelected(user.getEnabled());
			enabledField.selectedProperty().addListener((observable, oldValue, newValue) -> {
				user.setEnabled(newValue);
				isUserChanged = true;
			});
		}
	}

	@FXML
	private void editUser(ActionEvent event) {
		if (isUserChanged) {
			userRepository.save(user);
		}
		closeWindow((Node) event.getSource());
	}

	@FXML
	private void deleteUser(ActionEvent event) {
		userRepository.delete(user.getUserName());
		isUserRemoved = true;
		closeWindow((Node) event.getSource());
	}

	@FXML
	private void cancel(ActionEvent event) {
		closeWindow((Node) event.getSource());
	}

	private void closeWindow(Node node) {
		node.fireEvent(new WindowEvent(((Stage) node.getScene().getWindow()), WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public User getUser() {
		return user;
	}

	public boolean isUserChanged() {
		return isUserChanged;
	}

	public void setUser(User user) {
		isUserChanged = false;
		isUserRemoved = false;
		this.user = user;
	}

	public boolean isUserRemoved() {
		return isUserRemoved;
	}
}
