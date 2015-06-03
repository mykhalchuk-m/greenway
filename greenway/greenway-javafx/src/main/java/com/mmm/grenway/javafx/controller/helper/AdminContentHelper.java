package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.DocumentController;
import com.mmm.grenway.javafx.controller.UsersController;
import com.mmm.grenway.javafx.dto.UserDto;
import com.mmm.grenway.javafx.service.UserDtoService;
import com.mmm.grenway.javafx.service.converter.UserDtoConverter;

@Component
public class AdminContentHelper {
	private static final String EMPTY_STRING = "";

	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private UsersController usersController;
	@Autowired
	private UserDtoService userService;
	@Autowired
	private DocumentController documentController;

	public Tab generateAdminTab() {
		Tab adminTab = new Tab();
		adminTab.setText(resourceBundle.getString("main.tab.admin.title"));

		Tab generateReportTab = new Tab();
		generateReportTab.setText(resourceBundle.getString("main.tab.admin.report.title"));
		generateReportTab.setClosable(false);

		Tab documentTab = new Tab();
		documentTab.setText(resourceBundle.getString("main.tab.admin.doc.title"));
		documentTab.setClosable(false);
		documentTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				documentTab.setContent(screenConfig.getView(documentController, "DocumentPane.fxml"));
			}
		});

		Tab usersTab = new Tab();
		usersTab.setText(resourceBundle.getString("main.tab.admin.createuser.title"));
		usersTab.setClosable(false);
		usersTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				usersTab.setContent(screenConfig.getView(usersController, "UsersPane.fxml"));
			}
		});

		TabPane adminTabPane = new TabPane(generateReportTab, documentTab, usersTab);
		adminTabPane.setId("adminTabPane");
		adminTabPane.setSide(Side.LEFT);
		adminTab.setContent(adminTabPane);
		adminTab.setClosable(false);

		return adminTab;
	}

	public Node createUserForm(BiConsumer<ActionEvent, UserDto> c) {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setPrefWidth(400);

		Label userName = new Label(resourceBundle.getString("main.tab.admin.createuser.username.lable"));
		gridPane.add(userName, 0, 0);

		TextField userNameField = new TextField();
		gridPane.add(userNameField, 1, 0);

		Label password = new Label(resourceBundle.getString("main.tab.admin.createuser.password.lable"));
		gridPane.add(password, 0, 1);

		PasswordField passwordField = new PasswordField();
		gridPane.add(passwordField, 1, 1);

		Label confirmpassword = new Label(resourceBundle.getString("main.tab.admin.createuser.confirmpassword.lable"));
		gridPane.add(confirmpassword, 0, 2);

		PasswordField confirmPasswordField = new PasswordField();
		gridPane.add(confirmPasswordField, 1, 2);

		Label userRole = new Label(resourceBundle.getString("main.tab.admin.createuser.role.lable"));
		gridPane.add(userRole, 0, 3);

		ComboBox<String> roles = new ComboBox<String>(UserDtoConverter.getUserRoles());
		gridPane.add(roles, 1, 3);
		
		Label locationLable = new Label(resourceBundle.getString("main.tab.admin.createuser.location.lable"));
		gridPane.add(locationLable, 0, 4);
		
		ComboBox<String> locationSelect = new ComboBox<String>(userService.findAllLocations());
		locationSelect.getSelectionModel().select(0);
		locationSelect.setEditable(true);
		gridPane.add(locationSelect, 1, 4);

		Button btn = new Button(resourceBundle.getString("main.tab.admin.createuser.createuser"));
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		gridPane.add(hbBtn, 1, 5);

		ContextMenu userNameAlert = new ContextMenu(new MenuItem(
				resourceBundle.getString("main.tab.admin.createuser.validate.username")));
		userNameAlert.setAutoHide(false);
		ContextMenu passwordAlert = new ContextMenu(new MenuItem(
				resourceBundle.getString("main.tab.admin.createuser.validate.password")));
		passwordAlert.setAutoHide(false);
		ContextMenu confirmPasswordAlert = new ContextMenu(new MenuItem(
				resourceBundle.getString("main.tab.admin.createuser.validate.confirmpassword")));
		confirmPasswordAlert.setAutoHide(false);
		ContextMenu userRoleAlert = new ContextMenu(new MenuItem(
				resourceBundle.getString("main.tab.admin.createuser.validate.role")));
		userRoleAlert.setAutoHide(false);

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean isValid = true;
				if (!userNameAlert.isShowing() && EMPTY_STRING.equals(userNameField.getText())) {
					isValid = false;
					userNameAlert.show(userNameField, Side.RIGHT, 10, 0);
				}
				if (!passwordAlert.isShowing() && EMPTY_STRING.equals(passwordField.getText())) {
					isValid = false;
					passwordAlert.show(passwordField, Side.RIGHT, 10, 0);
				}
				if (!confirmPasswordAlert.isShowing()
						&& !passwordField.getText().equals(confirmPasswordField.getText())) {
					isValid = false;
					confirmPasswordAlert.show(confirmPasswordField, Side.RIGHT, 10, 0);
				}
				if (!userRoleAlert.isShowing() && roles.getSelectionModel().getSelectedItem() == null) {
					isValid = false;
					userRoleAlert.show(roles, Side.RIGHT, 10, 0);
				}
				if (isValid && !userNameAlert.isShowing() && !passwordAlert.isShowing() && !userRoleAlert.isShowing()
						&& !confirmPasswordAlert.isShowing()) {
					UserDto userDto = new UserDto();
					userDto.getUserName().set(userNameField.getText());
					userDto.getPassword().set(passwordField.getText());
					userDto.getRoles().set(roles.getSelectionModel().getSelectedItem());
					userDto.getLocation().set(locationSelect.getValue());
					c.accept(event, userDto);
					userNameField.clear();
					passwordField.clear();
					confirmPasswordField.clear();
					roles.getSelectionModel().select("");
					locationSelect.getSelectionModel().select("");
				}
			}
		});

		userNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					userNameAlert.hide();
				}
			}
		});

		passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					passwordAlert.hide();
				}
			}
		});

		confirmPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					confirmPasswordAlert.hide();
				}
			}
		});

		roles.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					userRoleAlert.hide();
				}
			}

		});

		gridPane.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userNameAlert.hide();
				passwordAlert.hide();
				userRoleAlert.hide();
				confirmPasswordAlert.hide();
			}
		});

		return gridPane;
	}

}
