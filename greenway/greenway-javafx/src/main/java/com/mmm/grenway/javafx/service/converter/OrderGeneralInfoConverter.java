package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.OrderGeneralInfo;
import com.mmm.greenway.entity.SexEnum;
import com.mmm.grenway.javafx.dto.OrderGeneralInfoDto;

public class OrderGeneralInfoConverter {
	public static void toOrderGeneralInfoDto(OrderGeneralInfoDto generalInfoDto, OrderGeneralInfo generalInfo) {
		generalInfoDto.getBirthCountry().set(generalInfo.getBirthCountry());
		generalInfoDto.getBirthDay().set(generalInfo.getBirthDay());
		generalInfoDto.getBirthNationality().set(generalInfo.getBirthNationality());
		generalInfoDto.getBirthPlace().set(generalInfo.getBirthPlace());
		generalInfoDto.getCurrentNationality().set(generalInfo.getCurrentNationality());
		generalInfoDto.getDestinationCountry().set(generalInfo.getDestinationCountry());
		generalInfoDto.getEndDateProviding().set(generalInfo.getEndDateProviding());
		generalInfoDto.getIsReferenceAvailable().set(generalInfo.getIsReferenceAvailable());
		generalInfoDto.getMartialStatus().set(generalInfo.getMartialStatus());
		generalInfoDto.getMaidenName().set(generalInfo.getMaidenName());
		generalInfoDto.getSecondNameInLatin().set(generalInfo.getSecondNameInLatin());
		generalInfoDto.getSex().set(generalInfo.getSex().name());
		generalInfoDto.getStartDocsProviding().set(generalInfo.getStartDocsProviding());
		generalInfoDto.getVisaType().set(generalInfo.getVisaType());
	}

	public static OrderGeneralInfo toOrderGeneralInfo(OrderGeneralInfoDto orderGeneralInfoDto) {
		OrderGeneralInfo orderGeneralInfo = new OrderGeneralInfo();
		orderGeneralInfo.setBirthCountry(orderGeneralInfoDto.getBirthCountry().get());
		orderGeneralInfo.setBirthDay(orderGeneralInfoDto.getBirthDay().get());
		orderGeneralInfo.setBirthNationality(orderGeneralInfoDto.getBirthNationality().get());
		orderGeneralInfo.setBirthPlace(orderGeneralInfoDto.getBirthPlace().get());
		orderGeneralInfo.setCurrentNationality(orderGeneralInfoDto.getCurrentNationality().get());
		orderGeneralInfo.setDestinationCountry(orderGeneralInfoDto.getDestinationCountry().get());
		orderGeneralInfo.setEndDateProviding(orderGeneralInfoDto.getEndDateProviding().get());
		orderGeneralInfo.setIsReferenceAvailable(orderGeneralInfoDto.getIsReferenceAvailable().get());
		orderGeneralInfo.setMaidenName(orderGeneralInfoDto.getMaidenName().get());
		orderGeneralInfo.setMartialStatus(orderGeneralInfoDto.getMartialStatus().get());
		orderGeneralInfo.setSecondNameInLatin(orderGeneralInfoDto.getSecondNameInLatin().get());
		String sex = orderGeneralInfoDto.getSex().get();
		orderGeneralInfo.setSex(sex == null ? null : SexEnum.valueOf(sex));
		orderGeneralInfo.setStartDocsProviding(orderGeneralInfoDto.getStartDocsProviding().get());
		orderGeneralInfo.setVisaType(orderGeneralInfoDto.getVisaType().get());
		return orderGeneralInfo;
	}
}
