package com.mmm.greenway.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderClientAddress {
	@Column(name = "clientCountry")
	private String country;
	@Column(name = "clientRegion")
	private String region;
	@Column(name = "clientDistrict")
	private String district;
	@Column(name = "clientLocality")
	private String locality;
	@Column(name = "clientZipCode")
	private String zipCode;
	@Column(name = "clientStreet")
	private String street;
	@Column(name = "clientEmail")
	private String email;
	@Column
	private String orderReceiverPhoneNumber;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderReceiverPhoneNumber() {
		return orderReceiverPhoneNumber;
	}

	public void setOrderReceiverPhoneNumber(String orderReceiverPhoneNumber) {
		this.orderReceiverPhoneNumber = orderReceiverPhoneNumber;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

}
