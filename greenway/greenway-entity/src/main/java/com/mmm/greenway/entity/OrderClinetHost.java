package com.mmm.greenway.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderClinetHost {
	@Column
	private String hostData;
	@Column
	private String country;
	@Column
	private String locality;
	@Column
	private String zipCode;
	@Column
	private String phone;
	@Column
	private String street;
	@Column
	private String email;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getHostData() {
		return hostData;
	}

	public void setHostData(String hostData) {
		this.hostData = hostData;
	}
}
