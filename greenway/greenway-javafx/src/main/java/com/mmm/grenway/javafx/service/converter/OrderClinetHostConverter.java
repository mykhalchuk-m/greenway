package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.OrderClinetHost;
import com.mmm.grenway.javafx.dto.OrderClinetHostDto;

public class OrderClinetHostConverter {
	public static void toOrderClinetHostDto(OrderClinetHostDto orderClinetHostDto, OrderClinetHost orderClinetHost) {
		orderClinetHostDto.getCountry().set(orderClinetHost.getCountry());
		orderClinetHostDto.getEmail().set(orderClinetHost.getEmail());
		orderClinetHostDto.getHostData().set(orderClinetHost.getHostData());
		orderClinetHostDto.getLocality().set(orderClinetHost.getLocality());
		orderClinetHostDto.getPhone().set(orderClinetHost.getPhone());
		orderClinetHostDto.getStreet().set(orderClinetHost.getStreet());
		orderClinetHostDto.getZipCode().set(orderClinetHost.getZipCode());
	}
	
	public static OrderClinetHost toOrderClinetHost(OrderClinetHostDto orderClinetHostDto) {
		OrderClinetHost orderClinetHost = new OrderClinetHost();
		orderClinetHost.setCountry(orderClinetHostDto.getCountry().get());
		orderClinetHost.setEmail(orderClinetHostDto.getEmail().get());
		orderClinetHost.setHostData(orderClinetHostDto.getHostData().get());
		orderClinetHost.setLocality(orderClinetHostDto.getLocality().get());
		orderClinetHost.setPhone(orderClinetHostDto.getPhone().get());
		orderClinetHost.setStreet(orderClinetHostDto.getStreet().get());
		orderClinetHost.setZipCode(orderClinetHostDto.getZipCode().get());
		return orderClinetHost;
	}
}
