package com.mmm.greenway.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detailed_order")
@Inheritance(strategy = InheritanceType.JOINED)
public class DetailedOrder extends BaseOrder {

	@Embedded
	private OrderGeneralInfo orderGeneralInfo;

	@Embedded
	private OrderPassportData orderPassportData;

	@Embedded
	private OrderClientAddress orderClientAddress;

	@Embedded
	private OrderClientWorkingPlace orderClientWorkingPlace;

	@Embedded
	private OrderClinetHost orderClinetHost;

	@Column
	private String travelPurpose;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "sutable_inout_dates_id")
	private DateInterval suitableInOutDates;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "prev_visa_dates_id")
	private List<DateInterval> previousVisasDates;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "invitation_id")
	private Invitation invitationDocument;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order", orphanRemoval = true)
	private List<DocumentPerOrder> documentPerOrders;
	@Column
	private LocalDate registrationDate;

	public OrderGeneralInfo getOrderGeneralInfo() {
		return orderGeneralInfo;
	}

	public void setOrderGeneralInfo(OrderGeneralInfo orderGeneralInfo) {
		this.orderGeneralInfo = orderGeneralInfo;
	}

	public OrderPassportData getOrderPassportData() {
		return orderPassportData;
	}

	public void setOrderPassportData(OrderPassportData orderPassportData) {
		this.orderPassportData = orderPassportData;
	}

	public OrderClientAddress getOrderClientAddress() {
		return orderClientAddress;
	}

	public void setOrderClientAddress(OrderClientAddress orderClientAddress) {
		this.orderClientAddress = orderClientAddress;
	}

	public OrderClientWorkingPlace getOrderClientWorkingPlace() {
		return orderClientWorkingPlace;
	}

	public void setOrderClientWorkingPlace(OrderClientWorkingPlace orderClientWorkingPlace) {
		this.orderClientWorkingPlace = orderClientWorkingPlace;
	}

	public OrderClinetHost getOrderClinetHost() {
		return orderClinetHost;
	}

	public void setOrderClinetHost(OrderClinetHost orderClinetHost) {
		this.orderClinetHost = orderClinetHost;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public List<DateInterval> getPreviousVisasDates() {
		return previousVisasDates;
	}

	public void setPreviousVisasDates(List<DateInterval> previousVisasDates) {
		this.previousVisasDates = previousVisasDates;
	}

	public DateInterval getSuitableInOutDates() {
		return suitableInOutDates;
	}

	public void setSuitableInOutDates(DateInterval suitableInOutDates) {
		this.suitableInOutDates = suitableInOutDates;
	}

	public List<DocumentPerOrder> getDocumentPerOrders() {
		return documentPerOrders;
	}

	public void setDocumentPerOrders(List<DocumentPerOrder> documentPerOrders) {
		this.documentPerOrders = documentPerOrders;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Invitation getInvitationDocument() {
		return invitationDocument;
	}

	public void setInvitationDocument(Invitation invitationDocument) {
		this.invitationDocument = invitationDocument;
	}
}
