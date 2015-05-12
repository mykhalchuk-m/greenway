package com.mmm.greenway.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@OneToOne
	private DateInterval sutableInOutDates;
	@OneToMany
	@JoinColumn(name = "prev_visa_dates_id")
	private List<DateInterval> previousVisasDates;

	@Enumerated(EnumType.STRING)
	private ProcessingStatus invitation;
	@Enumerated(EnumType.STRING)
	private ProcessingStatus registration;

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

	public DateInterval getSutableInOutDates() {
		return sutableInOutDates;
	}

	public void setSutableInOutDates(DateInterval sutableInOutDates) {
		this.sutableInOutDates = sutableInOutDates;
	}

	public List<DateInterval> getPreviousVisasDates() {
		return previousVisasDates;
	}

	public void setPreviousVisasDates(List<DateInterval> previousVisasDates) {
		this.previousVisasDates = previousVisasDates;
	}

	public ProcessingStatus getInvitation() {
		return invitation;
	}

	public void setInvitation(ProcessingStatus invitation) {
		this.invitation = invitation;
	}

	public ProcessingStatus getRegistration() {
		return registration;
	}

	public void setRegistration(ProcessingStatus registration) {
		this.registration = registration;
	}

}
