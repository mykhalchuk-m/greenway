package com.mmm.greenway.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "base_order")
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseOrder {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String supplierName;
	@Column
	private String phoneNumber;
	@ManyToOne(fetch = FetchType.EAGER)
	private User operator;
	@Column
	private String clientName;
	@Lob
	private String note;
	@Column
	private LocalDateTime date;
	
	@Enumerated(EnumType.STRING)
	private ProcessingStatus documentsStatus = ProcessingStatus.NONE;
	@Enumerated(EnumType.STRING)
	private ProcessingStatus registration = ProcessingStatus.NONE;
	@Enumerated(EnumType.STRING)
	private OrderType orderType = OrderType.CONSULT;
	@Column
	private Boolean isDone = false;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public ProcessingStatus getRegistration() {
		return registration;
	}

	public void setRegistration(ProcessingStatus registration) {
		this.registration = registration;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public ProcessingStatus getDocumentsStatus() {
		return documentsStatus;
	}

	public void setDocumentsStatus(ProcessingStatus documentsStatus) {
		this.documentsStatus = documentsStatus;
	}
	
	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
}
