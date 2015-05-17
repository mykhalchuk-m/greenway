package com.mmm.grenway.javafx.dto;

import com.mmm.greenway.entity.OrderClientAddress;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderClientAddressDto {
	private StringProperty country;
	private StringProperty region;
	private StringProperty district;
	private StringProperty locality;
	private StringProperty zipCode;
	private StringProperty street;
	private StringProperty email;
	private StringProperty orderReceiverPhoneNumber;

	public OrderClientAddressDto() {
		country = new SimpleStringProperty();
		region = new SimpleStringProperty();
		district = new SimpleStringProperty();
		locality = new SimpleStringProperty();
		zipCode = new SimpleStringProperty();
		street = new SimpleStringProperty();
		email = new SimpleStringProperty();
		orderReceiverPhoneNumber = new SimpleStringProperty();
	}

	public OrderClientAddressDto(OrderClientAddress orderClientAddress) {
		country = new SimpleStringProperty(orderClientAddress.getCountry());
		region = new SimpleStringProperty(orderClientAddress.getRegion());
		district = new SimpleStringProperty(orderClientAddress.getDistrict());
		locality = new SimpleStringProperty(orderClientAddress.getLocality());
		zipCode = new SimpleStringProperty(orderClientAddress.getZipCode());
		street = new SimpleStringProperty(orderClientAddress.getStreet());
		email = new SimpleStringProperty(orderClientAddress.getEmail());
		orderReceiverPhoneNumber = new SimpleStringProperty(orderClientAddress.getOrderReceiverPhoneNumber());
	}

	public StringProperty getCountry() {
		return country;
	}

	public void setCountry(StringProperty country) {
		this.country = country;
	}

	public StringProperty getRegion() {
		return region;
	}

	public void setRegion(StringProperty region) {
		this.region = region;
	}

	public StringProperty getDistrict() {
		return district;
	}

	public void setDistrict(StringProperty district) {
		this.district = district;
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

	public StringProperty getOrderReceiverPhoneNumber() {
		return orderReceiverPhoneNumber;
	}

	public void setOrderReceiverPhoneNumber(StringProperty orderReceiverPhoneNumber) {
		this.orderReceiverPhoneNumber = orderReceiverPhoneNumber;
	}
}
