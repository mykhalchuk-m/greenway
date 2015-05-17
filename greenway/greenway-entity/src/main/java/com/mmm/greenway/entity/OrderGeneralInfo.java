package com.mmm.greenway.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderGeneralInfo {
	@Column
	private String destinationCountry;
	@Column
	private String visaType;
	@Column
	private Boolean isReferenceAvailable;
	@Column
	private String secondNameInLatin;
	@Column
	private String maidenName;
	@Column
	private LocalDate birthDay;
	@Column
	private String birthPlace;
	@Column
	private String birthCountry;
	@Column
	private String currentNationality;
	@Column
	private String birthNationality;
	@Column
	private SexEnum sex;
	@Column
	private String martialStatus;
	@Column
	private LocalDate startDocsProviding;
	@Column
	private LocalDate endDateProviding;

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public Boolean getIsReferenceAvailable() {
		return isReferenceAvailable;
	}

	public void setIsReferenceAvailable(Boolean isReferenceAvailable) {
		this.isReferenceAvailable = isReferenceAvailable;
	}

	public String getSecondNameInLatin() {
		return secondNameInLatin;
	}

	public void setSecondNameInLatin(String secondNameInLatin) {
		this.secondNameInLatin = secondNameInLatin;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public String getCurrentNationality() {
		return currentNationality;
	}

	public void setCurrentNationality(String currentNationality) {
		this.currentNationality = currentNationality;
	}

	public String getBirthNationality() {
		return birthNationality;
	}

	public void setBirthNationality(String birthNationality) {
		this.birthNationality = birthNationality;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public LocalDate getStartDocsProviding() {
		return startDocsProviding;
	}

	public void setStartDocsProviding(LocalDate startDocsProviding) {
		this.startDocsProviding = startDocsProviding;
	}

	public LocalDate getEndDateProviding() {
		return endDateProviding;
	}

	public void setEndDateProviding(LocalDate endDateProviding) {
		this.endDateProviding = endDateProviding;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
}
