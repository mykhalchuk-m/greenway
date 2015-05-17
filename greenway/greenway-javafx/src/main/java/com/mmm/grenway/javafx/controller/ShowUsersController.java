package com.mmm.grenway.javafx.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.dto.UserDto;
import com.mmm.grenway.javafx.service.UserDtoService;
import com.mmm.grenway.javafx.service.converter.UserDtoConverter;

@Component
public class ShowUsersController {

	@Autowired
	private UserDtoService userDtoService;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private EditUserController editUserController;

	@FXML
	private TableView<UserDto> usersTable;
	@FXML
	private TableColumn<UserDto, String> userNameColumn;
	@FXML
	private TableColumn<UserDto, String> passwordColumn;
	@FXML
	private TableColumn<UserDto, String> rolesColumn;
//	@FXML
//	private TableColumn<UserDto, Boolean> accountNonExpiredColumn;
//	@FXML
//	private TableColumn<UserDto, Boolean> accountNonLockedColumn;
//	@FXML
//	private TableColumn<UserDto, Boolean> credentialsNonExpiredColumn;
//	@FXML
//	private TableColumn<UserDto, Boolean> enabledColumn;

	@FXML
	private TextField userNameFilter;
	@FXML
	private ComboBox<String> rolesFilter;

	@FXML
	private void initialize() {
		System.out.println("ShowUsersController");
		initTableCells();
		rolesFilter.setItems(UserDtoConverter.getUserRoles());
		initFiltersListeners();
		initPopupWindowForEdit();
	}

	private void initTableCells() {
		usersTable.setItems(userDtoService.findUsers());

		userNameColumn.setCellValueFactory(value -> value.getValue().getUserName());
		passwordColumn.setCellValueFactory(value -> value.getValue().getPassword());
		rolesColumn.setCellValueFactory(value -> value.getValue().getRoles());
//		enabledColumn.setCellValueFactory(value -> value.getValue().getEnabled());
//		enabledColumn.setCellFactory(new CheckBoxCellFactory());
//		accountNonExpiredColumn.setCellValueFactory(value -> value.getValue().getAccountNonExpired());
//		accountNonExpiredColumn.setCellFactory(new CheckBoxCellFactory());
//		accountNonLockedColumn.setCellValueFactory(value -> value.getValue().getAccountNonLocked());
//		accountNonLockedColumn.setCellFactory(new CheckBoxCellFactory());
//		credentialsNonExpiredColumn.setCellValueFactory(value -> value.getValue().getCredentialsNonExpired());
//		credentialsNonExpiredColumn.setCellFactory(new CheckBoxCellFactory());
	}

	private void initFiltersListeners() {
		rolesFilter.valueProperty().addListener((observable, oldValue, newValue) -> {
			String userNameFilterValue = userNameFilter.getText();
			usersTable.setItems(userDtoService.findUsers(userNameFilterValue, UserRole.valueOf(newValue)));
		});
		userNameFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			String roleFilterValue = rolesFilter.getSelectionModel().getSelectedItem();
			UserRole userRole = roleFilterValue == null ? null : UserRole.valueOf(roleFilterValue);
			usersTable.setItems(userDtoService.findUsers(newValue, userRole));
		});
	}

	private void initPopupWindowForEdit() {
		usersTable.setRowFactory(tr -> {
			TableRow<UserDto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && !row.isEmpty()) {
					UserDto userDto = row.getItem();
					User user = UserDtoConverter.toUser(userDto);
					editUserController.setUser(user);
					System.out.println(userDto);
					Stage stage = new Stage();
					stage.setTitle("Edit Page: ".concat(userDto.getUserName().get()));
					Scene scene = new Scene((Parent) screenConfig.getView(editUserController, "EditUserPane.fxml"),
							350, 350);
					stage.setScene(scene);
					stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<Event>() {

						@Override
						public void handle(Event event) {
							System.out.println("Close window");
							if (editUserController.isUserChanged()) {
								System.out.println("user was editted");
								userDto.applyChanges(editUserController.getUser());
							} else if (editUserController.isUserRemoved()){
								System.out.println("user was removed");
								usersTable.getItems().remove(userDto);
							}
						}
					});
					stage.show();
				}
			});
			return row;
		});
	}

//	private class CheckBoxCellFactory implements Callback<TableColumn<UserDto, Boolean>, TableCell<UserDto, Boolean>> {
//
//		@Override
//		public TableCell<UserDto, Boolean> call(TableColumn<UserDto, Boolean> param) {
//			return new CheckBoxTableCell<UserDto, Boolean>();
//		}
//
//	}

}
