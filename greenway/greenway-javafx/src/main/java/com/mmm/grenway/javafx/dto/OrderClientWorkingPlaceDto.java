package com.mmm.grenway.javafx.dto;

import com.mmm.greenway.entity.OrderClientWorkingPlace;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderClientWorkingPlaceDto {
	private StringProperty profession;
	private StringProperty country;
	private StringProperty region;
	private StringProperty district;
	private StringProperty locality;
	private StringProperty zipCode;
	private StringProperty street;
	private StringProperty phoneNumber;
	private StringProperty instructureName;
	private StringProperty email;

	public OrderClientWorkingPlaceDto() {
		profession = new SimpleStringProperty();
		country = new SimpleStringProperty();
		region = new SimpleStringProperty();
		district = new SimpleStringProperty();
		locality = new SimpleStringProperty();
		zipCode = new SimpleStringProperty();
		street = new SimpleStringProperty();
		phoneNumber = new SimpleStringProperty();
		instructureName = new SimpleStringProperty();
		email = new SimpleStringProperty();
	}

	public OrderClientWorkingPlaceDto(OrderClientWorkingPlace orderClientWorkingPlace) {
		profession = new SimpleStringProperty(orderClientWorkingPlace.getProfession());
		country = new SimpleStringProperty(orderClientWorkingPlace.getCountry());
		region = new SimpleStringProperty(orderClientWorkingPlace.getRegion());
		district = new SimpleStringProperty(orderClientWorkingPlace.getDistrict());
		locality = new SimpleStringProperty(orderClientWorkingPlace.getLocality());
		zipCode = new SimpleStringProperty(orderClientWorkingPlace.getZipCode());
		street = new SimpleStringProperty(orderClientWorkingPlace.getStreet());
		phoneNumber = new SimpleStringProperty(orderClientWorkingPlace.getPhoneNumber());
		instructureName = new SimpleStringProperty(orderClientWorkingPlace.getInstructureName());
		email = new SimpleStringProperty(orderClientWorkingPlace.getEmail());
	}

	public StringProperty getProfession() {
		return profession;
	}

	public void setProfession(StringProperty profession) {
		this.profession = profession;
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

	public StringProperty getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(StringProperty phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public StringProperty getInstructureName() {
		return instructureName;
	}

	public void setInstructureName(StringProperty instructureName) {
		this.instructureName = instructureName;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}
}
