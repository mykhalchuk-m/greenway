package com.mmm.grenway.javafx.service.converter;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.OrderType;
import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;

public class DetailedOrderConverter {
	public static DetailedOrder toDetailedOrder(DetailedOrderDto detailedOrderDto) {
		DetailedOrder detailedOrder = new DetailedOrder();
		detailedOrder.setId(detailedOrderDto.getId().get());
		detailedOrder.setClientName(detailedOrderDto.getClientName().get());
		detailedOrder.setDate(LocalDateTime.now());
		String orderType = detailedOrderDto.getOrderType().get();
		detailedOrder.setOrderType(orderType == null ? OrderType.CONSULT : OrderType.valueOf(orderType));
		String invitation = detailedOrderDto.getInvitation().get();
		detailedOrder.setInvitation(invitation == null ? ProcessingStatus.NONE : ProcessingStatus.valueOf(invitation));
		detailedOrder.setNote(detailedOrderDto.getNote().get());
		detailedOrder.setPhoneNumber(detailedOrderDto.getPhoneNumber().get());
		String registration = detailedOrderDto.getRegistration().get();
		detailedOrder.setRegistration(registration == null ? ProcessingStatus.NONE : ProcessingStatus
				.valueOf(registration));
		detailedOrder.setSupplierName(detailedOrderDto.getSupplierName().get());
		detailedOrder.setOrderClientAddress(OrderClientAddressConverter.toOrderClientAddress(detailedOrderDto
				.getOrderClientAddressDto()));
		detailedOrder.setOrderClientWorkingPlace(OrderClientWorkingPlaceConverter
				.toOrderClientWorkingPlace(detailedOrderDto.getOrderClientWorkingPlaceDto()));
		detailedOrder.setOrderClinetHost(OrderClinetHostConverter.toOrderClinetHost(detailedOrderDto
				.getOrderClinetHostDto()));
		detailedOrder.setOrderGeneralInfo(OrderGeneralInfoConverter.toOrderGeneralInfo(detailedOrderDto
				.getOrderGeneralInfoDto()));
		detailedOrder.setOrderPassportData(OrderPassportDataConverter.toOrderPassportData(detailedOrderDto
				.getOrderPassportDataDto()));
		detailedOrder.setSuitableInOutDates(DateIntervalConverter.toDateInterval(detailedOrderDto
				.getSuitableInOutDatesDto()));
		detailedOrder.setTravelPurpose(detailedOrderDto.getTravelPurpose().get());
		detailedOrder.setPreviousVisasDates(detailedOrderDto.getPreviousVisasDates().stream()
				.map(e -> DateIntervalConverter.toDateInterval(e)).collect(Collectors.toList()));
		return detailedOrder;
	}
}
