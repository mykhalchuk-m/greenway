package com.mmm.grenway.javafx.dto;

import java.util.stream.Collectors;

import com.mmm.greenway.entity.DetailedOrder;

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

	public DetailedOrderDto() {
		orderGeneralInfoDto = new OrderGeneralInfoDto();
		orderPassportDataDto = new OrderPassportDataDto();
		orderClientAddressDto = new OrderClientAddressDto();
		orderClientWorkingPlaceDto = new OrderClientWorkingPlaceDto();
		orderClinetHostDto = new OrderClinetHostDto();
		travelPurpose = new SimpleStringProperty();
		suitableInOutDatesDto = new DateIntervalDto();
		previousVisasDates = FXCollections.observableArrayList();
	}

	public DetailedOrderDto(DetailedOrder detailedOrder) {
		orderGeneralInfoDto = new OrderGeneralInfoDto(detailedOrder.getOrderGeneralInfo());
		orderPassportDataDto = new OrderPassportDataDto(detailedOrder.getOrderPassportData());
		orderClientAddressDto = new OrderClientAddressDto(detailedOrder.getOrderClientAddress());
		orderClientWorkingPlaceDto = new OrderClientWorkingPlaceDto(detailedOrder.getOrderClientWorkingPlace());
		orderClinetHostDto = new OrderClinetHostDto(detailedOrder.getOrderClinetHost());
		travelPurpose = new SimpleStringProperty(detailedOrder.getTravelPurpose());
		suitableInOutDatesDto = new DateIntervalDto(detailedOrder.getSuitableInOutDates());
		previousVisasDates = FXCollections.observableArrayList(detailedOrder.getPreviousVisasDates().stream()
				.map(e -> new DateIntervalDto(e)).collect(Collectors.toList()));
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
}
