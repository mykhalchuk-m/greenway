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
import com.mmm.grenway.javafx.dto.UserDto;
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

	private UserDto internalUserDto;
	private UserDto userDto = new UserDto();
	private boolean isUserChanged;
	private boolean isUserRemoved;

	@FXML
	private void initialize() {
		System.out.println("EditUserController");
		if (internalUserDto != null) {
			userNameFiled.textProperty().bindBidirectional(userDto.getUserName());
			userNameFiled.setText(internalUserDto.getUserName().get());
			userNameFiled.setEditable(false);
			passwordField.textProperty().bindBidirectional(userDto.getPassword());
			passwordField.setText(internalUserDto.getPassword().get());
			roleField.setItems(UserDtoConverter.getUserRoles());
			roleField.valueProperty().bindBidirectional(userDto.getRoles());
			roleField.setValue(internalUserDto.getRoles().get());
			accountNonExpiredField.selectedProperty().bindBidirectional(userDto.getAccountNonExpired());
			accountNonExpiredField.setSelected(internalUserDto.getAccountNonExpired().get());
			accountNonLockedField.selectedProperty().bindBidirectional(userDto.getAccountNonLocked());
			accountNonLockedField.setSelected(internalUserDto.getAccountNonLocked().get());
			credentialsNonExpiredField.selectedProperty().bindBidirectional(userDto.getCredentialsNonExpired());
			credentialsNonExpiredField.setSelected(internalUserDto.getCredentialsNonExpired().get());
			enabledField.selectedProperty().bindBidirectional(userDto.getEnabled());
			enabledField.setSelected(internalUserDto.getEnabled().get());
		}
	}

	@FXML
	private void editUser(ActionEvent event) {
		isUserChanged = true;
		closeWindow((Node) event.getSource());
	}

	@FXML
	private void deleteUser(ActionEvent event) {
		isUserRemoved = true;
		closeWindow((Node) event.getSource());
	}

	@FXML
	private void cancel(ActionEvent event) {
		isUserRemoved = false;
		isUserChanged = false;
		closeWindow((Node) event.getSource());
	}

	private void closeWindow(Node node) {
		node.fireEvent(new WindowEvent(((Stage) node.getScene().getWindow()), WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public UserDto getUser() {
		return userDto;
	}

	public void setUser(UserDto userDto) {
		isUserChanged = false;
		isUserRemoved = false;
		this.internalUserDto = userDto;
	}
	
	public boolean isUserChanged() {
		return isUserChanged;
	}

	public boolean isUserRemoved() {
		return isUserRemoved;
	}
}
