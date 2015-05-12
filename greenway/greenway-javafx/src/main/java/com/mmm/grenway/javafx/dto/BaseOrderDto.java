package com.mmm.grenway.javafx.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.grenway.javafx.util.DateUtil;

public class BaseOrderDto {
	private StringProperty supplierProp;
	private StringProperty clientNameProp;
	private StringProperty clientPhoneProp;
	private StringProperty operatorProp;
	private StringProperty noteProp;
	private StringProperty dateProp;
	private StringProperty statusProp;

	public BaseOrderDto(BaseOrder baseOrder) {
		supplierProp = new SimpleStringProperty(baseOrder.getSupplierName());
		clientNameProp = new SimpleStringProperty(baseOrder.getClientName());
		clientPhoneProp = new SimpleStringProperty(baseOrder.getPhoneNumber());
		operatorProp = new SimpleStringProperty(baseOrder.getOperator().getUserName());
		dateProp = new SimpleStringProperty(DateUtil.format(baseOrder.getDate()));
		statusProp = new SimpleStringProperty();
		noteProp = new SimpleStringProperty(baseOrder.getNote());
	}

	public StringProperty getSupplierProp() {
		return supplierProp;
	}

	public void setSupplierProp(StringProperty supplierProp) {
		this.supplierProp = supplierProp;
	}

	public StringProperty getClientNameProp() {
		return clientNameProp;
	}

	public void setClientNameProp(StringProperty clientNameProp) {
		this.clientNameProp = clientNameProp;
	}

	public StringProperty getClientPhoneProp() {
		return clientPhoneProp;
	}

	public void setClientPhoneProp(StringProperty clientPhoneProp) {
		this.clientPhoneProp = clientPhoneProp;
	}

	public StringProperty getOperatorProp() {
		return operatorProp;
	}

	public void setOperatorProp(StringProperty operatorProp) {
		this.operatorProp = operatorProp;
	}

	public StringProperty getNoteProp() {
		return noteProp;
	}

	public void setNoteProp(StringProperty noteProp) {
		this.noteProp = noteProp;
	}

	public StringProperty getDateProp() {
		return dateProp;
	}

	public void setDateProp(StringProperty dateProp) {
		this.dateProp = dateProp;
	}

	public StringProperty getStatusProp() {
		return statusProp;
	}

	public void setStatusProp(StringProperty statusProp) {
		this.statusProp = statusProp;
	}
}
