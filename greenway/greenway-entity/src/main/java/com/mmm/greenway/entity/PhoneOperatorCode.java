package com.mmm.greenway.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_operator_code")
public class PhoneOperatorCode {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String code;

	public PhoneOperatorCode() {
	}
	
	public PhoneOperatorCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
