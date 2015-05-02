package com.mmm.grenway.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

import org.springframework.beans.factory.annotation.Autowired;

import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.dto.UserDto;
import com.mmm.grenway.javafx.service.UserDtoService;
import com.mmm.grenway.javafx.service.converter.UserDtoConverter;

public class ShowUsersController {

	@Autowired
	private UserDtoService userDtoService;

	@FXML
	private TableView<UserDto> usersTable;
	@FXML
	private TableColumn<UserDto, String> userNameColumn;
	@FXML
	private TableColumn<UserDto, String> passwordColumn;
	@FXML
	private TableColumn<UserDto, String> rolesColumn;
	@FXML
	private TableColumn<UserDto, Boolean> accountNonExpiredColumn;
	@FXML
	private TableColumn<UserDto, Boolean> accountNonLockedColumn;
	@FXML
	private TableColumn<UserDto, Boolean> credentialsNonExpiredColumn;
	@FXML
	private TableColumn<UserDto, Boolean> enabledColumn;

	@FXML
	private TextField userNameFilter;
	@FXML
	private ComboBox<String> rolesFilter;

	@FXML
	private void initialize() {
		System.out.println("ShowUsersController");
		initTableCells();
		rolesFilter.setItems(UserDtoConverter.getUserRoles());
		rolesFilter.valueProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println(newValue);
			usersTable.setItems(userDtoService.findUsersWithRoles(UserRole.valueOf(newValue)));
		});
		userNameFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			usersTable.setItems(userDtoService.findUsersStartsWithName(newValue));
		});
	}

	private void initTableCells() {
		usersTable.setItems(userDtoService.findUsers());

		userNameColumn.setCellValueFactory(value -> value.getValue().getUserName());
		passwordColumn.setCellValueFactory(value -> value.getValue().getPassword());
		rolesColumn.setCellValueFactory(value -> value.getValue().getRoles());
		enabledColumn.setCellValueFactory(value -> value.getValue().getEnabled());
		enabledColumn.setCellFactory(new CheckBoxCellFactory());
		accountNonExpiredColumn.setCellValueFactory(value -> value.getValue().getAccountNonExpired());
		accountNonExpiredColumn.setCellFactory(new CheckBoxCellFactory());
		accountNonLockedColumn.setCellValueFactory(value -> value.getValue().getAccountNonLocked());
		accountNonLockedColumn.setCellFactory(new CheckBoxCellFactory());
		credentialsNonExpiredColumn.setCellValueFactory(value -> value.getValue().getCredentialsNonExpired());
		credentialsNonExpiredColumn.setCellFactory(new CheckBoxCellFactory());
	}

	private class CheckBoxCellFactory implements Callback<TableColumn<UserDto, Boolean>, TableCell<UserDto, Boolean>> {

		@Override
		public TableCell<UserDto, Boolean> call(TableColumn<UserDto, Boolean> param) {
			return new CheckBoxTableCell<UserDto, Boolean>();
		}

	}

}
