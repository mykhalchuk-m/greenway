package com.mmm.greenway.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderPassportData {
	@Column
	private String socialId;
	@Column
	private String foringPassportNumber;
	@Column
	private Date foringPassportStartDate;
	@Column
	private String foringPassportExpDate;
	@Column
	private String foringPassportIssuedBy;

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getForingPassportNumber() {
		return foringPassportNumber;
	}

	public void setForingPassportNumber(String foringPassportNumber) {
		this.foringPassportNumber = foringPassportNumber;
	}

	public Date getForingPassportStartDate() {
		return foringPassportStartDate;
	}

	public void setForingPassportStartDate(Date foringPassportStartDate) {
		this.foringPassportStartDate = foringPassportStartDate;
	}

	public String getForingPassportExpDate() {
		return foringPassportExpDate;
	}

	public void setForingPassportExpDate(String foringPassportExpDate) {
		this.foringPassportExpDate = foringPassportExpDate;
	}

	public String getForingPassportIssuedBy() {
		return foringPassportIssuedBy;
	}

	public void setForingPassportIssuedBy(String foringPassportIssuedBy) {
		this.foringPassportIssuedBy = foringPassportIssuedBy;
	}
}
