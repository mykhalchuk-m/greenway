package com.mmm.grenway.javafx.dto;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.grenway.javafx.util.DateUtil;

public class BaseOrderDto {
	private LongProperty id;
	private StringProperty supplierName;
	private StringProperty clientName;
	private StringProperty phoneNumber;
	private StringProperty operator;
	private StringProperty note;
	private StringProperty date;
	private StringProperty documnentsStatus;
	private StringProperty registration;
	private StringProperty orderType;

	public BaseOrderDto(BaseOrder baseOrder) {
		id = new SimpleLongProperty(baseOrder.getId());
		supplierName = new SimpleStringProperty(baseOrder.getSupplierName());
		clientName = new SimpleStringProperty(baseOrder.getClientName());
		phoneNumber = new SimpleStringProperty(baseOrder.getPhoneNumber());
		setOperator(new SimpleStringProperty(baseOrder.getOperator().getUserName()));
		setDate(new SimpleStringProperty(DateUtil.format(baseOrder.getDate())));
		documnentsStatus = new SimpleStringProperty(baseOrder.getDocumentsStatus().name());
		setRegistration(new SimpleStringProperty(baseOrder.getRegistration().name()));
		note = new SimpleStringProperty(baseOrder.getNote());
		setOrderType(new SimpleStringProperty(baseOrder.getOrderType().name()));
	}

	public BaseOrderDto() {
		id = new SimpleLongProperty();
		supplierName = new SimpleStringProperty();
		clientName = new SimpleStringProperty();
		phoneNumber = new SimpleStringProperty();
		setOperator(new SimpleStringProperty());
		setDate(new SimpleStringProperty());
		documnentsStatus = new SimpleStringProperty();
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

	public StringProperty getOrderType() {
		return orderType;
	}

	public void setOrderType(StringProperty orderType) {
		this.orderType = orderType;
	}

	public LongProperty getId() {
		return id;
	}

	public void setId(LongProperty id) {
		this.id = id;
	}

	public StringProperty getDocumnentsStatus() {
		return documnentsStatus;
	}

	public void setDocumnentsStatus(StringProperty documnentsStatus) {
		this.documnentsStatus = documnentsStatus;
	}
}
