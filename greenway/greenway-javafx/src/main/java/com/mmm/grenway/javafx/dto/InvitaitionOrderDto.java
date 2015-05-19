package com.mmm.grenway.javafx.dto;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.DetailedOrder;

public class InvitaitionOrderDto {
	private LongProperty orderId;
	private StringProperty clientName;
	private ObjectProperty<LocalDate> birthDay;
	private StringProperty foringPassportNumber;
	private StringProperty region;
	private ObjectProperty<LocalDate> registrationDate;
	private DoubleProperty cost;
	private StringProperty invitationSupplier;
	private StringProperty status;
	
	public InvitaitionOrderDto() {
		orderId = new SimpleLongProperty();
		clientName = new SimpleStringProperty();
		birthDay = new SimpleObjectProperty<LocalDate>();
		foringPassportNumber = new SimpleStringProperty();
		region = new SimpleStringProperty();
		registrationDate = new SimpleObjectProperty<LocalDate>();
		cost = new SimpleDoubleProperty();
		invitationSupplier = new SimpleStringProperty();
		status = new SimpleStringProperty();
	}
	
	public InvitaitionOrderDto(DetailedOrder detailedOrder) {
		orderId = new SimpleLongProperty(detailedOrder.getId());
		clientName = new SimpleStringProperty(detailedOrder.getClientName());
		birthDay = new SimpleObjectProperty<LocalDate>(detailedOrder.getOrderGeneralInfo().getBirthDay());
		foringPassportNumber = new SimpleStringProperty(detailedOrder.getOrderPassportData().getForingPassportNumber());
		region = new SimpleStringProperty(detailedOrder.getOrderClientAddress().getRegion());
		registrationDate = new SimpleObjectProperty<LocalDate>();
		cost = new SimpleDoubleProperty();
		invitationSupplier = new SimpleStringProperty();
		status = new SimpleStringProperty(detailedOrder.getInvitation().name());
	}

	public LongProperty getOrderId() {
		return orderId;
	}

	public void setOrderId(LongProperty orderId) {
		this.orderId = orderId;
	}

	public StringProperty getClientName() {
		return clientName;
	}

	public void setClientName(StringProperty clientName) {
		this.clientName = clientName;
	}

	public ObjectProperty<LocalDate> getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(ObjectProperty<LocalDate> birthDay) {
		this.birthDay = birthDay;
	}

	public StringProperty getForingPassportNumber() {
		return foringPassportNumber;
	}

	public void setForingPassportNumber(StringProperty foringPassportNumber) {
		this.foringPassportNumber = foringPassportNumber;
	}

	public StringProperty getRegion() {
		return region;
	}

	public void setRegion(StringProperty region) {
		this.region = region;
	}

	public ObjectProperty<LocalDate> getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(ObjectProperty<LocalDate> registrationDate) {
		this.registrationDate = registrationDate;
	}

	public DoubleProperty getCost() {
		return cost;
	}

	public void setCost(DoubleProperty cost) {
		this.cost = cost;
	}

	public StringProperty getInvitationSupplier() {
		return invitationSupplier;
	}

	public void setInvitationSupplier(StringProperty invitationSupplier) {
		this.invitationSupplier = invitationSupplier;
	}

	public StringProperty getStatus() {
		return status;
	}

	public void setStatus(StringProperty status) {
		this.status = status;
	}
}
