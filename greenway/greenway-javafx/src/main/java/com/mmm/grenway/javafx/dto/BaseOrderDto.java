package com.mmm.grenway.javafx.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.OrderType;
import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.util.DateUtil;

public class BaseOrderDto {
	private LongProperty id;
	private StringProperty supplierName;
	private StringProperty clientName;
	private StringProperty phoneNumber;
	private StringProperty operator;
	private StringProperty note;
	private StringProperty date;
	private ObjectProperty<ProcessingStatus> documnentsStatus;
	private ObjectProperty<ProcessingStatus> registration;
	private ObjectProperty<OrderType> orderType;
	private BooleanProperty isDone;

	public BaseOrderDto(BaseOrder baseOrder) {
		id = new SimpleLongProperty(baseOrder.getId());
		supplierName = new SimpleStringProperty(baseOrder.getSupplierName());
		clientName = new SimpleStringProperty(baseOrder.getClientName());
		phoneNumber = new SimpleStringProperty(baseOrder.getPhoneNumber());
		operator = new SimpleStringProperty(baseOrder.getOperator().getUserName());
		date = new SimpleStringProperty(DateUtil.format(baseOrder.getDate()));
		documnentsStatus = new SimpleObjectProperty<ProcessingStatus>(baseOrder.getDocumentsStatus());
		registration = new SimpleObjectProperty<ProcessingStatus>(baseOrder.getRegistration());
		note = new SimpleStringProperty(baseOrder.getNote());
		orderType = new SimpleObjectProperty<OrderType>(baseOrder.getOrderType());
		isDone = new SimpleBooleanProperty(baseOrder.getIsDone());
	}

	public BaseOrderDto() {
		id = new SimpleLongProperty();
		supplierName = new SimpleStringProperty();
		clientName = new SimpleStringProperty();
		phoneNumber = new SimpleStringProperty();
		setOperator(new SimpleStringProperty());
		setDate(new SimpleStringProperty());
		documnentsStatus = new SimpleObjectProperty<ProcessingStatus>();
		registration = new SimpleObjectProperty<ProcessingStatus>();
		note = new SimpleStringProperty();
		orderType = new SimpleObjectProperty<OrderType>();
		isDone = new SimpleBooleanProperty(false);
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

	public ObjectProperty<ProcessingStatus> getRegistration() {
		return registration;
	}

	public void setRegistration(ObjectProperty<ProcessingStatus> registration) {
		this.registration = registration;
	}

	public ObjectProperty<OrderType> getOrderType() {
		return orderType;
	}

	public void setOrderType(ObjectProperty<OrderType> orderType) {
		this.orderType = orderType;
	}

	public LongProperty getId() {
		return id;
	}

	public void setId(LongProperty id) {
		this.id = id;
	}

	public ObjectProperty<ProcessingStatus> getDocumnentsStatus() {
		return documnentsStatus;
	}

	public void setDocumnentsStatus(ObjectProperty<ProcessingStatus> documnentsStatus) {
		this.documnentsStatus = documnentsStatus;
	}

	public BooleanProperty getIsDone() {
		return isDone;
	}

	public void setIsDone(BooleanProperty isDone) {
		this.isDone = isDone;
	}
}
