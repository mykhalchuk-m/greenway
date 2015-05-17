package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.OrderPassportData;
import com.mmm.grenway.javafx.dto.OrderPassportDataDto;

public class OrderPassportDataConverter {
	public static void toOrderPassportDataDto(OrderPassportDataDto orderPassportDataDto,
			OrderPassportData orderPassportData) {
		orderPassportDataDto.getForingPassportExpDate().set(orderPassportData.getForingPassportExpDate());
		orderPassportDataDto.getForingPassportIssuedBy().set(orderPassportData.getForingPassportIssuedBy());
		orderPassportDataDto.getForingPassportNumber().set(orderPassportData.getForingPassportNumber());
		orderPassportDataDto.getForingPassportStartDate().set(orderPassportData.getForingPassportStartDate());
		orderPassportDataDto.getSocialId().set(orderPassportData.getSocialId());
	}

	public static OrderPassportData toOrderPassportData(OrderPassportDataDto orderPassportDataDto) {
		OrderPassportData orderPassportData = new OrderPassportData();
		orderPassportData.setForingPassportExpDate(orderPassportDataDto.getForingPassportExpDate().get());
		orderPassportData.setForingPassportIssuedBy(orderPassportDataDto.getForingPassportIssuedBy().get());
		orderPassportData.setForingPassportNumber(orderPassportDataDto.getForingPassportNumber().get());
		orderPassportData.setForingPassportStartDate(orderPassportDataDto.getForingPassportStartDate().get());
		orderPassportData.setSocialId(orderPassportDataDto.getSocialId().get());
		return orderPassportData;
	}
}
