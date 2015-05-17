package com.mmm.grenway.javafx.dto;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.OrderPassportData;

public class OrderPassportDataDto {
	private StringProperty socialId;
	private StringProperty foringPassportNumber;
	private ObjectProperty<LocalDate> foringPassportStartDate;
	private ObjectProperty<LocalDate> foringPassportExpDate;
	private StringProperty foringPassportIssuedBy;

	public OrderPassportDataDto() {
		socialId = new SimpleStringProperty();
		foringPassportNumber = new SimpleStringProperty();
		foringPassportStartDate = new SimpleObjectProperty<>();
		setForingPassportExpDate(new SimpleObjectProperty<>());
		foringPassportIssuedBy = new SimpleStringProperty();
	}

	public OrderPassportDataDto(OrderPassportData orderPassportData) {
		socialId = new SimpleStringProperty(orderPassportData.getSocialId());
		foringPassportNumber = new SimpleStringProperty(orderPassportData.getForingPassportNumber());
		foringPassportStartDate = new SimpleObjectProperty<>(orderPassportData.getForingPassportStartDate());
		setForingPassportExpDate(new SimpleObjectProperty<>(orderPassportData.getForingPassportExpDate()));
		foringPassportIssuedBy = new SimpleStringProperty(orderPassportData.getForingPassportIssuedBy());
	}

	public StringProperty getSocialId() {
		return socialId;
	}

	public void setSocialId(StringProperty socialId) {
		this.socialId = socialId;
	}

	public StringProperty getForingPassportNumber() {
		return foringPassportNumber;
	}

	public void setForingPassportNumber(StringProperty foringPassportNumber) {
		this.foringPassportNumber = foringPassportNumber;
	}

	public ObjectProperty<LocalDate> getForingPassportStartDate() {
		return foringPassportStartDate;
	}

	public void setForingPassportStartDate(ObjectProperty<LocalDate> foringPassportStartDate) {
		this.foringPassportStartDate = foringPassportStartDate;
	}

	public StringProperty getForingPassportIssuedBy() {
		return foringPassportIssuedBy;
	}

	public void setForingPassportIssuedBy(StringProperty foringPassportIssuedBy) {
		this.foringPassportIssuedBy = foringPassportIssuedBy;
	}

	public ObjectProperty<LocalDate> getForingPassportExpDate() {
		return foringPassportExpDate;
	}

	public void setForingPassportExpDate(ObjectProperty<LocalDate> foringPassportExpDate) {
		this.foringPassportExpDate = foringPassportExpDate;
	}
}
