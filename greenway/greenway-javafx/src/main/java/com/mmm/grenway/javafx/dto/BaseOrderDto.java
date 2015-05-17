package com.mmm.grenway.javafx.dto;

import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.BaseOrder;

public class BaseOrderDto {
	private StringProperty supplierName;
	private StringProperty clientName;
	private StringProperty phoneNumber;
	private StringProperty operator;
	private StringProperty note;
	private StringProperty date;
	private StringProperty invitation;
	private StringProperty registration;
	private StringProperty orderType;

	public BaseOrderDto(BaseOrder baseOrder) {
		supplierName = new SimpleStringProperty(baseOrder.getSupplierName());
		clientName = new SimpleStringProperty(baseOrder.getClientName());
		phoneNumber = new SimpleStringProperty(baseOrder.getPhoneNumber());
		setOperator(new SimpleStringProperty(baseOrder.getOperator().getUserName()));
		setDate(new SimpleStringProperty(baseOrder.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
		setInvitation(new SimpleStringProperty(baseOrder.getInvitation().name()));
		setRegistration(new SimpleStringProperty(baseOrder.getRegistration().name()));
		note = new SimpleStringProperty(baseOrder.getNote());
		setOrderType(new SimpleStringProperty(baseOrder.getOrderType().name()));
	}

	public BaseOrderDto() {
		supplierName = new SimpleStringProperty();
		clientName = new SimpleStringProperty();
		phoneNumber = new SimpleStringProperty();
		setOperator(new SimpleStringProperty());
		setDate(new SimpleStringProperty());
		setInvitation(new SimpleStringProperty());
		setRegistration(new SimpleStringProperty());
		note = new SimpleStringProperty();
		orderType = new SimpleStringProperty();
	}

	public StringProperty getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(StringProperty supplierName) {
		this.supplierName = supplierName;
	}

	public StringProperty getClientName() {
		return clientName;
	}

	public void setClientName(StringProperty clientName) {
		this.clientName = clientName;
	}

	public StringProperty getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(StringProperty phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public StringProperty getNote() {
		return note;
	}

	public void setNote(StringProperty note) {
		this.note = note;
	}

	public StringProperty getOperator() {
		return operator;
	}

	public void setOperator(StringProperty operator) {
		this.operator = operator;
	}

	public StringProperty getDate() {
		return date;
	}

	public void setDate(StringProperty date) {
		this.date = date;
	}

	public StringProperty getRegistration() {
		return registration;
	}

	public void setRegistration(StringProperty registration) {
		this.registration = registration;
	}

	public StringProperty getInvitation() {
		return invitation;
	}

	public void setInvitation(StringProperty invitation) {
		this.invitation = invitation;
	}

	public StringProperty getOrderType() {
		return orderType;
	}

	public void setOrderType(StringProperty orderType) {
		this.orderType = orderType;
	}
}
