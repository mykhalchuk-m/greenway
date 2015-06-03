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
	private StringProperty location;

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
		roles = new SimpleStringProperty(user.getRole() == null ? "" : user.getRole().name());
		location = new SimpleStringProperty(user.getLocation());
	}

	public void applyChanges(UserDto userDto) {
		if (!password.get().equals(userDto.getPassword().get())) {
			password.set(userDto.getPassword().get());
		}
		if (!roles.get().equals(userDto.getRoles().get())) {
			roles.setValue(userDto.getRoles().get());
		}
		if (accountNonExpired.get() != userDto.getAccountNonExpired().get()) {
			accountNonExpired.setValue(userDto.getAccountNonExpired().get());
		}
		if (accountNonLocked.get() != userDto.getAccountNonLocked().get()) {
			accountNonLocked.setValue(userDto.getAccountNonLocked().get());
		}
		if (credentialsNonExpired.get() != userDto.getCredentialsNonExpired().get()) {
			credentialsNonExpired.setValue(userDto.getCredentialsNonExpired().get());
		}
		if (enabled.get() != userDto.getEnabled().get()) {
			enabled.setValue(userDto.getEnabled().get());
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

	public StringProperty getLocation() {
		return location;
	}

	public void setLocation(StringProperty location) {
		this.location = location;
	}
}
