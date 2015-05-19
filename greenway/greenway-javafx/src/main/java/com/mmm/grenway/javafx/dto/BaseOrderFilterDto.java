package com.mmm.grenway.javafx.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BaseOrderFilterDto {
	private StringProperty clientNameFilter;
	private StringProperty phoneNumberFilter;
	
	public BaseOrderFilterDto() {
		setClientNameFilter(new SimpleStringProperty(""));
		setPhoneNumberFilter(new SimpleStringProperty(""));
	}

	public StringProperty getClientNameFilter() {
		return clientNameFilter;
	}

	public void setClientNameFilter(StringProperty clientNameFilter) {
		this.clientNameFilter = clientNameFilter;
	}

	public StringProperty getPhoneNumberFilter() {
		return phoneNumberFilter;
	}

	public void setPhoneNumberFilter(StringProperty phoneNumberFilter) {
		this.phoneNumberFilter = phoneNumberFilter;
	}
}
