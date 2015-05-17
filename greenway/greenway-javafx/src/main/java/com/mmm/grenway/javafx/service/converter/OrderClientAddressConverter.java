package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.OrderClientAddress;
import com.mmm.grenway.javafx.dto.OrderClientAddressDto;

public class OrderClientAddressConverter {
	public static void toOrderClientAddressDto(OrderClientAddressDto orderClientAddressDto,
			OrderClientAddress orderClientAddress) {
		orderClientAddressDto.getCountry().set(orderClientAddress.getCountry());
		orderClientAddressDto.getDistrict().set(orderClientAddress.getDistrict());
		orderClientAddressDto.getEmail().set(orderClientAddress.getEmail());
		orderClientAddressDto.getLocality().set(orderClientAddress.getLocality());
		orderClientAddressDto.getOrderReceiverPhoneNumber().set(orderClientAddress.getOrderReceiverPhoneNumber());
		orderClientAddressDto.getRegion().set(orderClientAddress.getRegion());
		orderClientAddressDto.getStreet().set(orderClientAddress.getStreet());
		orderClientAddressDto.getZipCode().set(orderClientAddress.getZipCode());
	}
	
	public static OrderClientAddress toOrderClientAddress(OrderClientAddressDto orderClientAddressDto) {
		OrderClientAddress orderClientAddress = new OrderClientAddress();
		orderClientAddress.setCountry(orderClientAddressDto.getCountry().get());
		orderClientAddress.setDistrict(orderClientAddressDto.getDistrict().get());
		orderClientAddress.setEmail(orderClientAddressDto.getEmail().get());
		orderClientAddress.setLocality(orderClientAddressDto.getLocality().get());
		orderClientAddress.setOrderReceiverPhoneNumber(orderClientAddressDto.getOrderReceiverPhoneNumber().get());
		orderClientAddress.setRegion(orderClientAddressDto.getRegion().get());
		orderClientAddress.setStreet(orderClientAddressDto.getStreet().get());
		orderClientAddress.setZipCode(orderClientAddressDto.getZipCode().get());
		return orderClientAddress;
	}
}
