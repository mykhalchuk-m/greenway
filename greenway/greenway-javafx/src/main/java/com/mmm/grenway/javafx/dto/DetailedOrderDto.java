package com.mmm.grenway.javafx.dto;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.mmm.greenway.entity.DetailedOrder;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DetailedOrderDto extends BaseOrderDto {
	private OrderGeneralInfoDto orderGeneralInfoDto;
	private OrderPassportDataDto orderPassportDataDto;
	private OrderClientAddressDto orderClientAddressDto;
	private OrderClientWorkingPlaceDto orderClientWorkingPlaceDto;
	private OrderClinetHostDto orderClinetHostDto;
	private StringProperty travelPurpose;
	private DateIntervalDto suitableInOutDatesDto;
	private ObservableList<DateIntervalDto> previousVisasDates;
	private ObservableList<DocumentPerOrderDto> documentPerOrder;
	private DocumentPerOrderDto invitationDocument;
	private ObjectProperty<LocalDate> registrationDate;

	public DetailedOrderDto() {
		super();
		orderGeneralInfoDto = new OrderGeneralInfoDto();
		orderPassportDataDto = new OrderPassportDataDto();
		orderClientAddressDto = new OrderClientAddressDto();
		orderClientWorkingPlaceDto = new OrderClientWorkingPlaceDto();
		orderClinetHostDto = new OrderClinetHostDto();
		travelPurpose = new SimpleStringProperty();
		suitableInOutDatesDto = new DateIntervalDto();
		previousVisasDates = FXCollections.observableArrayList();
		documentPerOrder = FXCollections.observableArrayList();
		invitationDocument = new DocumentPerOrderDto();
		registrationDate = new SimpleObjectProperty<LocalDate>();
	}

	public DetailedOrderDto(DetailedOrder detailedOrder) {
		super(detailedOrder);
		orderGeneralInfoDto = new OrderGeneralInfoDto(detailedOrder.getOrderGeneralInfo());
		orderPassportDataDto = new OrderPassportDataDto(detailedOrder.getOrderPassportData());
		orderClientAddressDto = new OrderClientAddressDto(detailedOrder.getOrderClientAddress());
		orderClientWorkingPlaceDto = new OrderClientWorkingPlaceDto(detailedOrder.getOrderClientWorkingPlace());
		orderClinetHostDto = new OrderClinetHostDto(detailedOrder.getOrderClinetHost());
		travelPurpose = new SimpleStringProperty(detailedOrder.getTravelPurpose());
		suitableInOutDatesDto = new DateIntervalDto(detailedOrder.getSuitableInOutDates());
		previousVisasDates = FXCollections.observableArrayList(detailedOrder.getPreviousVisasDates().stream()
				.map(e -> new DateIntervalDto(e)).collect(Collectors.toList()));
		documentPerOrder = FXCollections.observableArrayList();
		detailedOrder.getDocumentPerOrders().forEach(d -> documentPerOrder.add(new DocumentPerOrderDto(d)));
		invitationDocument = detailedOrder.getInvitationDocument() == null ? new DocumentPerOrderDto()
				: new DocumentPerOrderDto(detailedOrder.getInvitationDocument());
		registrationDate = new SimpleObjectProperty<LocalDate>(detailedOrder.getRegistrationDate());
	}

	public OrderGeneralInfoDto getOrderGeneralInfoDto() {
		return orderGeneralInfoDto;
	}

	public void setOrderGeneralInfoDto(OrderGeneralInfoDto orderGeneralInfoDto) {
		this.orderGeneralInfoDto = orderGeneralInfoDto;
	}

	public OrderPassportDataDto getOrderPassportDataDto() {
		return orderPassportDataDto;
	}

	public void setOrderPassportDataDto(OrderPassportDataDto orderPassportDataDto) {
		this.orderPassportDataDto = orderPassportDataDto;
	}

	public OrderClientAddressDto getOrderClientAddressDto() {
		return orderClientAddressDto;
	}

	public void setOrderClientAddressDto(OrderClientAddressDto orderClientAddressDto) {
		this.orderClientAddressDto = orderClientAddressDto;
	}

	public OrderClientWorkingPlaceDto getOrderClientWorkingPlaceDto() {
		return orderClientWorkingPlaceDto;
	}

	public void setOrderClientWorkingPlaceDto(OrderClientWorkingPlaceDto orderClientWorkingPlaceDto) {
		this.orderClientWorkingPlaceDto = orderClientWorkingPlaceDto;
	}

	public OrderClinetHostDto getOrderClinetHostDto() {
		return orderClinetHostDto;
	}

	public void setOrderClinetHostDto(OrderClinetHostDto orderClinetHostDto) {
		this.orderClinetHostDto = orderClinetHostDto;
	}

	public StringProperty getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(StringProperty travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public DateIntervalDto getSuitableInOutDatesDto() {
		return suitableInOutDatesDto;
	}

	public void setSuitableInOutDatesDto(DateIntervalDto suitableInOutDatesDto) {
		this.suitableInOutDatesDto = suitableInOutDatesDto;
	}

	public ObservableList<DateIntervalDto> getPreviousVisasDates() {
		return previousVisasDates;
	}

	public void setPreviousVisasDates(ObservableList<DateIntervalDto> previousVisasDates) {
		this.previousVisasDates = previousVisasDates;
	}

	public ObservableList<DocumentPerOrderDto> getDocumentPerOrder() {
		return documentPerOrder;
	}

	public void setDocumentPerOrder(ObservableList<DocumentPerOrderDto> documentPerOrder) {
		this.documentPerOrder = documentPerOrder;
	}

	public DocumentPerOrderDto getInvitationDocument() {
		return invitationDocument;
	}

	public void setInvitationDocument(DocumentPerOrderDto invitationDocument) {
		this.invitationDocument = invitationDocument;
	}

	public ObjectProperty<LocalDate> getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(ObjectProperty<LocalDate> registrationDate) {
		this.registrationDate = registrationDate;
	}
}
