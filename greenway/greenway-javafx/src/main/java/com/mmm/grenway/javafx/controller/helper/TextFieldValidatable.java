package com.mmm.grenway.javafx.controller.helper;

import java.util.regex.Pattern;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class TextFieldValidatable extends TextField {
	private boolean isValid = true;
	private boolean isActive = true;

	private String defaultRerex = ".+";
	private String regex;
	private String regexErrorMessage;
	private ContextMenu validationAlert = new ContextMenu();

	public void initValidator(String validationMessage, String regex, String regexErrorMessage) {
		this.regex = regex;
		this.regexErrorMessage = regexErrorMessage;
		initValidator(validationMessage);
	}

	public void initValidator(String validationMessage) {
		MenuItem menuItem = new MenuItem(validationMessage);
		validationAlert.getItems().add(menuItem);
		validationAlert.setAutoHide(false);

		focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				validationAlert.hide();
			}
		});

	}

	public void validate() {
		if (!validationAlert.isShowing()) {
			isValid = true;
		}
		if (isActive && !validationAlert.isShowing()) {
			if (!Pattern.matches(defaultRerex, this.getText())) {
				validationAlert.show(this, Side.RIGHT, 10, 0);
				isValid = false;
			} else if (regex != null && !Pattern.matches(regex, this.getText())) {
				validationAlert.getItems().get(0).setText(regexErrorMessage);
				validationAlert.show(this, Side.RIGHT, 10, 0);
				isValid = false;
			}
		}
	}

	public void hideValidationAlert() {
		if (validationAlert.isShowing()) {
			validationAlert.hide();
		}
	}

	public boolean isValid() {
		return isValid;
	}
	
	public void disableValidation() {
		isActive = false;
		isValid = true;
	}
	
	public void enableValidation() {
		isActive = true;
	}

}
