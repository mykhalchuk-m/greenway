package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.CreateUserController;
import com.mmm.grenway.javafx.controller.ShowUsersController;
import com.mmm.grenway.javafx.service.converter.UserDtoConverter;

@Component
public class AdminContentHelper {
	private static final String EMPTY_STRING = "";
	
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private CreateUserController createUserController;
	@Autowired
	private ShowUsersController showUsersController;

	public Tab generateAdminTab() {
		Tab adminTab = new Tab();
		adminTab.setText(resourceBundle.getString("main.tab.admin.title"));

		Tab generateReportTab = new Tab();
		generateReportTab.setText(resourceBundle.getString("main.tab.admin.report.title"));
		generateReportTab.setClosable(false);

		Tab createUserTab = new Tab();
		createUserTab.setText(resourceBundle.getString("main.tab.admin.createuser.title"));
		createUserTab.setClosable(false);
		createUserTab.setContent(screenConfig.getView(createUserController, "CreateUserPane.fxml"));

		Tab showAllUsersTab = new Tab();
		showAllUsersTab.setText(resourceBundle.getString("main.tab.admin.showusers.title"));
		showAllUsersTab.setClosable(false);
		showAllUsersTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (showAllUsersTab.isSelected()) {
					showAllUsersTab.setContent(screenConfig.getView(showUsersController, "ShowUsersPane.fxml"));
				}
			}

		});

		TabPane adminTabPane = new TabPane(generateReportTab, createUserTab, showAllUsersTab);
		adminTabPane.setId("adminTabPane");
		adminTabPane.setSide(Side.LEFT);
		adminTab.setContent(adminTabPane);

		return adminTab;
	}
	
	public Node createUserForm(BiConsumer<ActionEvent, User> c) {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 20, 20, 20));

		Label userName = new Label("User Name:");
		gridPane.add(userName, 0, 0);

		TextField userNameField = new TextField();
		gridPane.add(userNameField, 1, 0);

		Label password = new Label("Password:");
		gridPane.add(password, 0, 1);

		PasswordField passwordField = new PasswordField();
		gridPane.add(passwordField, 1, 1);

		Label confirmpassword = new Label("Confirm password:");
		gridPane.add(confirmpassword, 0, 2);

		PasswordField confirmPasswordField = new PasswordField();
		gridPane.add(confirmPasswordField, 1, 2);

		Label userRole = new Label("User Role:");
		gridPane.add(userRole, 0, 3);

		ComboBox<String> roles = new ComboBox<String>(UserDtoConverter.getUserRoles());
		gridPane.add(roles, 1, 3);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		gridPane.add(hbBtn, 1, 4);

		ContextMenu userNameAlert = new ContextMenu(new MenuItem("User name is required"));
		userNameAlert.setAutoHide(false);
		ContextMenu passwordAlert = new ContextMenu(new MenuItem("Password is required"));
		passwordAlert.setAutoHide(false);
		ContextMenu confirmPasswordAlert = new ContextMenu(new MenuItem("Password doesn't match"));
		confirmPasswordAlert.setAutoHide(false);
		ContextMenu userRoleAlert = new ContextMenu(new MenuItem("Select one of role"));
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
					User user = new User();
					user.setUserName(userNameField.getText());
					user.setPassword(passwordField.getText());
					user.setRole(UserRole.valueOf(roles.getSelectionModel().getSelectedItem()));
					c.accept(event, user);
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
