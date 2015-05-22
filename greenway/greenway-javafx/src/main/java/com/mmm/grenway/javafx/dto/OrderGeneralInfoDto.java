package com.mmm.grenway.javafx.dto;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.OrderGeneralInfo;

public class OrderGeneralInfoDto {
	private StringProperty destinationCountry;
	private StringProperty visaType;
	private BooleanProperty isReferenceAvailable;
	private StringProperty secondNameInLatin;
	private StringProperty maidenName;
	private ObjectProperty<LocalDate> birthDay;
	private StringProperty birthPlace;
	private StringProperty birthCountry;
	private StringProperty currentNationality;
	private StringProperty birthNationality;
	private StringProperty sex;
	private StringProperty martialStatus;
	private ObjectProperty<LocalDate> startDocsProviding;
	private ObjectProperty<LocalDate> endDateProviding;

	public OrderGeneralInfoDto() {
		destinationCountry = new SimpleStringProperty();
		visaType = new SimpleStringProperty();
		isReferenceAvailable = new SimpleBooleanProperty();
		secondNameInLatin = new SimpleStringProperty();
		maidenName = new SimpleStringProperty();
		birthDay = new SimpleObjectProperty<>();
		birthPlace = new SimpleStringProperty();
		birthCountry = new SimpleStringProperty();
		currentNationality = new SimpleStringProperty();
		birthNationality = new SimpleStringProperty();
		sex = new SimpleStringProperty();
		martialStatus = new SimpleStringProperty();
		startDocsProviding = new SimpleObjectProperty<>();
		endDateProviding = new SimpleObjectProperty<>();
		maidenName = new SimpleStringProperty();
	}

	public OrderGeneralInfoDto(OrderGeneralInfo orderGeneralInfo) {
		destinationCountry = new SimpleStringProperty(orderGeneralInfo.getDestinationCountry());
		visaType = new SimpleStringProperty(orderGeneralInfo.getVisaType());
		isReferenceAvailable = new SimpleBooleanProperty(orderGeneralInfo.getIsReferenceAvailable());
		secondNameInLatin = new SimpleStringProperty(orderGeneralInfo.getSecondNameInLatin());
		birthDay = new SimpleObjectProperty<>(orderGeneralInfo.getBirthDay());
		birthPlace = new SimpleStringProperty(orderGeneralInfo.getBirthPlace());
		birthCountry = new SimpleStringProperty(orderGeneralInfo.getBirthCountry());
		currentNationality = new SimpleStringProperty(orderGeneralInfo.getCurrentNationality());
		birthNationality = new SimpleStringProperty(orderGeneralInfo.getBirthNationality());
		sex = new SimpleStringProperty(orderGeneralInfo.getSex() == null ? "" : orderGeneralInfo.getSex().name());
		martialStatus = new SimpleStringProperty(orderGeneralInfo.getMartialStatus());
		startDocsProviding = new SimpleObjectProperty<>(orderGeneralInfo.getStartDocsProviding());
		endDateProviding = new SimpleObjectProperty<>(orderGeneralInfo.getEndDateProviding());
		maidenName = new SimpleStringProperty(orderGeneralInfo.getMaidenName());
	}
	
	public StringProperty getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(StringProperty destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public StringProperty getVisaType() {
		return visaType;
	}

	public void setVisaType(StringProperty visaType) {
		this.visaType = visaType;
	}

	public BooleanProperty getIsReferenceAvailable() {
		return isReferenceAvailable;
	}

	public void setIsReferenceAvailable(BooleanProperty isReferenceAvailable) {
		this.isReferenceAvailable = isReferenceAvailable;
	}

	public StringProperty getSecondNameInLatin() {
		return secondNameInLatin;
	}

	public void setSecondNameInLatin(StringProperty secondNameInLatin) {
		this.secondNameInLatin = secondNameInLatin;
	}

	public ObjectProperty<LocalDate> getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(ObjectProperty<LocalDate> birthDay) {
		this.birthDay = birthDay;
	}

	public StringProperty getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(StringProperty birthPlace) {
		this.birthPlace = birthPlace;
	}

	public StringProperty getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(StringProperty birthCountry) {
		this.birthCountry = birthCountry;
	}

	public StringProperty getCurrentNationality() {
		return currentNationality;
	}

	public void setCurrentNationality(StringProperty currentNationality) {
		this.currentNationality = currentNationality;
	}

	public StringProperty getBirthNationality() {
		return birthNationality;
	}

	public void setBirthNationality(StringProperty birthNationality) {
		this.birthNationality = birthNationality;
	}

	public StringProperty getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(StringProperty martialStatus) {
		this.martialStatus = martialStatus;
	}

	public ObjectProperty<LocalDate> getStartDocsProviding() {
		return startDocsProviding;
	}

	public void setStartDocsProviding(ObjectProperty<LocalDate> startDocsProviding) {
		this.startDocsProviding = startDocsProviding;
	}

	public ObjectProperty<LocalDate> getEndDateProviding() {
		return endDateProviding;
	}

	public void setEndDateProviding(ObjectProperty<LocalDate> endDateProviding) {
		this.endDateProviding = endDateProviding;
	}

	public StringProperty getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(StringProperty maidenName) {
		this.maidenName = maidenName;
	}

	public StringProperty getSex() {
		return sex;
	}

	public void setSex(StringProperty sex) {
		this.sex = sex;
	}
}
