package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.OrderClientWorkingPlace;
import com.mmm.grenway.javafx.dto.OrderClientWorkingPlaceDto;

public class OrderClientWorkingPlaceConverter {
	public static void toOrderClientWorkingPlaceDto(OrderClientWorkingPlaceDto clientWorkingPlaceDto,
			OrderClientWorkingPlace clientWorkingPlace) {
		clientWorkingPlaceDto.getCountry().set(clientWorkingPlace.getCountry());
		clientWorkingPlaceDto.getDistrict().set(clientWorkingPlace.getDistrict());
		clientWorkingPlaceDto.getEmail().set(clientWorkingPlace.getEmail());
		clientWorkingPlaceDto.getInstructureName().set(clientWorkingPlace.getInstructureName());
		clientWorkingPlaceDto.getLocality().set(clientWorkingPlace.getLocality());
		clientWorkingPlaceDto.getPhoneNumber().set(clientWorkingPlace.getPhoneNumber());
		clientWorkingPlaceDto.getProfession().set(clientWorkingPlace.getProfession());
		clientWorkingPlaceDto.getRegion().set(clientWorkingPlace.getRegion());
		clientWorkingPlaceDto.getStreet().set(clientWorkingPlace.getStreet());
		clientWorkingPlaceDto.getZipCode().set(clientWorkingPlace.getZipCode());
	}

	public static OrderClientWorkingPlace toOrderClientWorkingPlace(OrderClientWorkingPlaceDto clientWorkingPlaceDto) {
		OrderClientWorkingPlace orderClientWorkingPlace = new OrderClientWorkingPlace();
		orderClientWorkingPlace.setCountry(clientWorkingPlaceDto.getCountry().get());
		orderClientWorkingPlace.setDistrict(clientWorkingPlaceDto.getDistrict().get());
		orderClientWorkingPlace.setEmail(clientWorkingPlaceDto.getEmail().get());
		orderClientWorkingPlace.setInstructureName(clientWorkingPlaceDto.getInstructureName().get());
		orderClientWorkingPlace.setLocality(clientWorkingPlaceDto.getLocality().get());
		orderClientWorkingPlace.setPhoneNumber(clientWorkingPlaceDto.getPhoneNumber().get());
		orderClientWorkingPlace.setProfession(clientWorkingPlaceDto.getProfession().get());
		orderClientWorkingPlace.setRegion(clientWorkingPlaceDto.getRegion().get());
		orderClientWorkingPlace.setStreet(clientWorkingPlaceDto.getStreet().get());
		orderClientWorkingPlace.setZipCode(clientWorkingPlaceDto.getZipCode().get());
		return orderClientWorkingPlace;
	}
}
