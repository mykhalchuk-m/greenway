package com.mmm.grenway.javafx.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.User;

public class UserDto {
	private StringProperty userName;
	private StringProperty password;
	private BooleanProperty enabled;
	private BooleanProperty accountNonExpired;
	private BooleanProperty accountNonLocked;
	private BooleanProperty credentialsNonExpired;
	private StringProperty roles;

	public UserDto() {
		this(new User());
	}

	public UserDto(User user) {
		userName = new SimpleStringProperty(user.getUserName());
		password = new SimpleStringProperty(user.getPassword());
		accountNonExpired = new SimpleBooleanProperty(user.getAccountNonExpired());
		accountNonLocked = new SimpleBooleanProperty(user.getAccountNonLocked());
		credentialsNonExpired = new SimpleBooleanProperty(user.getCredentialsNonExpired());
		enabled = new SimpleBooleanProperty(user.getEnabled());
		roles = new SimpleStringProperty(user.getRole().toString());
	}
	
	public void applyChanges(User user) {
		if (!password.get().equals(user.getPassword())) {
			password.set(user.getPassword());
		}
		if (!roles.get().equals(user.getRole().toString())) {
			roles.setValue(user.getRole().toString());
		}
		if (accountNonExpired.get() != user.getAccountNonExpired()) {
			accountNonExpired.setValue(user.getAccountNonExpired());
		}
		if (accountNonLocked.get() != user.getAccountNonLocked()) {
			accountNonLocked.setValue(user.getAccountNonLocked());
		}
		if (credentialsNonExpired.get() != user.getCredentialsNonExpired()) {
			credentialsNonExpired.setValue(user.getCredentialsNonExpired());
		}
		if (enabled.get() != user.getEnabled()) {
			enabled.setValue(user.getEnabled());
		}
	}

	public StringProperty getUserName() {
		return userName;
	}

	public void setUserName(StringProperty userName) {
		this.userName = userName;
	}

	public StringProperty getPassword() {
		return password;
	}

	public void setPassword(StringProperty password) {
		this.password = password;
	}

	public BooleanProperty getEnabled() {
		return enabled;
	}

	public void setEnabled(BooleanProperty enabled) {
		this.enabled = enabled;
	}

	public BooleanProperty getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(BooleanProperty accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public BooleanProperty getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(BooleanProperty accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public BooleanProperty getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(BooleanProperty credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public StringProperty getRoles() {
		return roles;
	}

	public void setRoles(StringProperty roles) {
		this.roles = roles;
	}
}
