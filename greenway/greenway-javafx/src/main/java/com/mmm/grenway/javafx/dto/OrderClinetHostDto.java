package com.mmm.grenway.javafx.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.OrderClinetHost;

public class OrderClinetHostDto {
	private StringProperty hostData;
	private StringProperty country;
	private StringProperty locality;
	private StringProperty zipCode;
	private StringProperty phone;
	private StringProperty street;
	private StringProperty email;

	public OrderClinetHostDto() {
		hostData = new SimpleStringProperty();
		country = new SimpleStringProperty();
		locality = new SimpleStringProperty();
		zipCode = new SimpleStringProperty();
		phone = new SimpleStringProperty();
		street = new SimpleStringProperty();
		email = new SimpleStringProperty();
	}

	public OrderClinetHostDto(OrderClinetHost orderClinetHost) {
		hostData = new SimpleStringProperty(orderClinetHost.getHostData());
		country = new SimpleStringProperty(orderClinetHost.getCountry());
		locality = new SimpleStringProperty(orderClinetHost.getLocality());
		zipCode = new SimpleStringProperty(orderClinetHost.getZipCode());
		phone = new SimpleStringProperty(orderClinetHost.getPhone());
		street = new SimpleStringProperty(orderClinetHost.getStreet());
		email = new SimpleStringProperty(orderClinetHost.getEmail());
	}

	public StringProperty getHostData() {
		return hostData;
	}

	public void setHostData(StringProperty hostData) {
		this.hostData = hostData;
	}

	public StringProperty getCountry() {
		return country;
	}

	public void setCountry(StringProperty country) {
		this.country = country;
	}

	public StringProperty getLocality() {
		return locality;
	}

	public void setLocality(StringProperty locality) {
		this.locality = locality;
	}

	public StringProperty getZipCode() {
		return zipCode;
	}

	public void setZipCode(StringProperty zipCode) {
		this.zipCode = zipCode;
	}

	public StringProperty getPhone() {
		return phone;
	}

	public void setPhone(StringProperty phone) {
		this.phone = phone;
	}

	public StringProperty getStreet() {
		return street;
	}

	public void setStreet(StringProperty street) {
		this.street = street;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}
}
