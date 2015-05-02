package com.mmm.grenway.javafx.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;

public class CreateUserForm {

	private static final String EMPTY_STRING = "";

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

		List<CheckBox> roles = Arrays.asList(UserRole.values()).stream().map(role -> new CheckBox(role.toString()))
				.collect(Collectors.toList());
		VBox rolesContainer = new VBox(3);
		rolesContainer.getChildren().addAll(roles);
		gridPane.add(rolesContainer, 1, 3, 1, 4);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		gridPane.add(hbBtn, 1, roles.size() + 4);

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
				if (!userRoleAlert.isShowing()
						&& roles.stream().filter(p -> p.isSelected()).collect(Collectors.toList()).isEmpty()) {
					isValid = false;
					double dx = passwordField.getWidth() - roles.get(0).getWidth() + 10;
					userRoleAlert.show(roles.get(0), Side.RIGHT, dx, 0);
				}
				if (isValid && !userNameAlert.isShowing() && !passwordAlert.isShowing() && !userRoleAlert.isShowing()
						&& !confirmPasswordAlert.isShowing()) {
					User user = new User();
					user.setUserName(userNameField.getText());
					user.setPassword(passwordField.getText());
					user.setRoles(roles.stream().filter(p -> p.isSelected()).map(m -> UserRole.valueOf(m.getText()))
							.collect(Collectors.toList()));
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

		roles.forEach(role -> role.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					userRoleAlert.hide();
				}
			}
		}));

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
