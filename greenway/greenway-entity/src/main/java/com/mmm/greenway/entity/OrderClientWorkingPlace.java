package com.mmm.greenway.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderClientWorkingPlace {
	@Column(name="workPlaceProfession")
	private String profession;
	@Column(name= "workPlaceCountry")
	private String country;
	@Column(name="workPlaceRegion")
	private String region;
	@Column(name ="workPlaceDistrict")
	private String district;
	@Column(name="workPlaceLocality")
	private String locality;
	@Column(name="workPlaceZipCode")
	private String zipCode;
	@Column(name="workPlaceStreet")
	private String street;
	@Column(name="workPlacePhoneNumber")
	private String phoneNumber;
	@Column(name="workPlaceInstructureName")
	private String instructureName;
	@Column(name="workPlaceEmail")
	private String email;

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getInstructureName() {
		return instructureName;
	}

	public void setInstructureName(String instructureName) {
		this.instructureName = instructureName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
