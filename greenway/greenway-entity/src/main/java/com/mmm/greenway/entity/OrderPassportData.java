package com.mmm.greenway.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderPassportData {
	@Column
	private String socialId;
	@Column
	private String foringPassportNumber;
	@Column
	private LocalDate foringPassportStartDate;
	@Column
	private LocalDate foringPassportExpDate;
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

	public String getForingPassportIssuedBy() {
		return foringPassportIssuedBy;
	}

	public void setForingPassportIssuedBy(String foringPassportIssuedBy) {
		this.foringPassportIssuedBy = foringPassportIssuedBy;
	}

	public LocalDate getForingPassportStartDate() {
		return foringPassportStartDate;
	}

	public void setForingPassportStartDate(LocalDate foringPassportStartDate) {
		this.foringPassportStartDate = foringPassportStartDate;
	}

	public LocalDate getForingPassportExpDate() {
		return foringPassportExpDate;
	}

	public void setForingPassportExpDate(LocalDate foringPassportExpDate) {
		this.foringPassportExpDate = foringPassportExpDate;
	}
}
