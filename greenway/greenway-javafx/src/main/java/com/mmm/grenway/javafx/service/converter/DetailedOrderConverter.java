package com.mmm.grenway.javafx.service.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.DocumentPerOrder;
import com.mmm.greenway.entity.Invitation;
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
		String documentsStatus = detailedOrderDto.getDocumnentsStatus().get();
		detailedOrder.setDocumentsStatus(documentsStatus == null ? ProcessingStatus.NONE : ProcessingStatus
				.valueOf(documentsStatus));
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
		List<DocumentPerOrder> documentsPerOrder = new ArrayList<>();
		detailedOrderDto.getDocumentPerOrder().forEach(
				e -> {
					DocumentPerOrder documentPerOrder = new DocumentPerOrder();
					documentPerOrder.setId(e.getId() == null ? null : e.getId().get());
					documentPerOrder.setProcessingStatus(!e.getProcessingStatus().get().isEmpty() ? ProcessingStatus
							.valueOf(e.getProcessingStatus().get()) : ProcessingStatus.NONE);
					documentPerOrder.setDocument(DocumentConverter.toDocument(e.getDocument()));
					documentsPerOrder.add(documentPerOrder);
				});
		detailedOrder.setDocumentPerOrders(documentsPerOrder);
		if (detailedOrderDto.getInvitationDocument() != null) {
			Invitation invitationDocument = new Invitation();
			if (detailedOrderDto.getId().getValue() != null) {
				invitationDocument.setId(detailedOrderDto.getId().get());
			}
			invitationDocument.setTitle(detailedOrderDto.getInvitationDocument().getTitle().get());
			invitationDocument.setPrice(detailedOrderDto.getInvitationDocument().getPrice().get());
			String invitationDocumentProcessStatus = detailedOrderDto.getInvitationDocument().getStatus().get();
			if (invitationDocumentProcessStatus != null && !invitationDocumentProcessStatus.isEmpty()) {
				invitationDocument.setProcessingStatus(ProcessingStatus.valueOf(invitationDocumentProcessStatus));
			}
			detailedOrder.setInvitationDocument(invitationDocument);
		}
		if (detailedOrderDto.getRegistrationDate().get() != null) {
			detailedOrder.setRegistrationDate(detailedOrderDto.getRegistrationDate().get());
		}
		return detailedOrder;
	}
	
	public static void enrichWithBaseOrder(DetailedOrderDto detailedOrderDto, BaseOrder baseOrder) {
		detailedOrderDto.getId().set(baseOrder.getId());
		detailedOrderDto.getClientName().set(baseOrder.getClientName());
		detailedOrderDto.getSupplierName().set(baseOrder.getSupplierName());
		detailedOrderDto.getNote().set(baseOrder.getNote());
		detailedOrderDto.getOperator().set(baseOrder.getOperator().getUserName());
		detailedOrderDto.getPhoneNumber().set(baseOrder.getPhoneNumber());
	}

	public static void enrichDtoWithEntity(DetailedOrderDto detailedOrderDto, DetailedOrder detailedOrder) {
		detailedOrderDto.getId().set(detailedOrder.getId());
		detailedOrderDto.getClientName().set(detailedOrder.getClientName());
		detailedOrderDto.getSupplierName().set(detailedOrder.getSupplierName());
		detailedOrderDto.getNote().set(detailedOrder.getNote());
		detailedOrderDto.getOperator().set(detailedOrder.getOperator().getUserName());
		detailedOrderDto.getPhoneNumber().set(detailedOrder.getPhoneNumber());

		detailedOrderDto.getOrderClientAddressDto().getCountry()
				.set(detailedOrder.getOrderClientAddress().getCountry());
		detailedOrderDto.getOrderClientAddressDto().getDistrict()
				.set(detailedOrder.getOrderClientAddress().getDistrict());
		detailedOrderDto.getOrderClientAddressDto().getEmail().set(detailedOrder.getOrderClientAddress().getEmail());
		detailedOrderDto.getOrderClientAddressDto().getLocality()
				.set(detailedOrder.getOrderClientAddress().getLocality());
		detailedOrderDto.getOrderClientAddressDto().getOrderReceiverPhoneNumber()
				.set(detailedOrder.getOrderClientAddress().getOrderReceiverPhoneNumber());
		detailedOrderDto.getOrderClientAddressDto().getRegion().set(detailedOrder.getOrderClientAddress().getRegion());
		detailedOrderDto.getOrderClientAddressDto().getStreet().set(detailedOrder.getOrderClientAddress().getStreet());
		detailedOrderDto.getOrderClientAddressDto().getZipCode()
				.set(detailedOrder.getOrderClientAddress().getZipCode());

		detailedOrderDto.getOrderClientWorkingPlaceDto().getCountry()
				.set(detailedOrder.getOrderClientWorkingPlace().getCountry());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getDistrict()
				.set(detailedOrder.getOrderClientWorkingPlace().getDistrict());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getEmail()
				.set(detailedOrder.getOrderClientWorkingPlace().getEmail());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getInstructureName()
				.set(detailedOrder.getOrderClientWorkingPlace().getInstructureName());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getLocality()
				.set(detailedOrder.getOrderClientWorkingPlace().getLocality());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getPhoneNumber()
				.set(detailedOrder.getOrderClientWorkingPlace().getPhoneNumber());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getProfession()
				.set(detailedOrder.getOrderClientWorkingPlace().getProfession());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getRegion()
				.set(detailedOrder.getOrderClientWorkingPlace().getRegion());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getStreet()
				.set(detailedOrder.getOrderClientWorkingPlace().getStreet());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getZipCode()
				.set(detailedOrder.getOrderClientWorkingPlace().getZipCode());

		detailedOrderDto.getOrderClinetHostDto().getCountry().set(detailedOrder.getOrderClinetHost().getCountry());
		detailedOrderDto.getOrderClinetHostDto().getEmail().set(detailedOrder.getOrderClinetHost().getEmail());
		detailedOrderDto.getOrderClinetHostDto().getHostData().set(detailedOrder.getOrderClinetHost().getHostData());
		detailedOrderDto.getOrderClinetHostDto().getLocality().set(detailedOrder.getOrderClinetHost().getLocality());
		detailedOrderDto.getOrderClinetHostDto().getPhone().set(detailedOrder.getOrderClinetHost().getPhone());
		detailedOrderDto.getOrderClinetHostDto().getStreet().set(detailedOrder.getOrderClinetHost().getStreet());
		detailedOrderDto.getOrderClinetHostDto().getZipCode().set(detailedOrder.getOrderClinetHost().getZipCode());

		detailedOrderDto.getOrderGeneralInfoDto().getBirthCountry()
				.set(detailedOrder.getOrderGeneralInfo().getBirthCountry());
		detailedOrderDto.getOrderGeneralInfoDto().getBirthDay().set(detailedOrder.getOrderGeneralInfo().getBirthDay());
		detailedOrderDto.getOrderGeneralInfoDto().getBirthNationality()
				.set(detailedOrder.getOrderGeneralInfo().getBirthNationality());
		detailedOrderDto.getOrderGeneralInfoDto().getBirthPlace()
				.set(detailedOrder.getOrderGeneralInfo().getBirthPlace());
		detailedOrderDto.getOrderGeneralInfoDto().getCurrentNationality()
				.set(detailedOrder.getOrderGeneralInfo().getCurrentNationality());
		detailedOrderDto.getOrderGeneralInfoDto().getDestinationCountry()
				.set(detailedOrder.getOrderGeneralInfo().getDestinationCountry());
		detailedOrderDto.getOrderGeneralInfoDto().getEndDateProviding()
				.set(detailedOrder.getOrderGeneralInfo().getEndDateProviding());
		detailedOrderDto.getOrderGeneralInfoDto().getIsReferenceAvailable()
				.set(detailedOrder.getOrderGeneralInfo().getIsReferenceAvailable());
		detailedOrderDto.getOrderGeneralInfoDto().getMaidenName()
				.set(detailedOrder.getOrderGeneralInfo().getMaidenName());
		detailedOrderDto.getOrderGeneralInfoDto().getMartialStatus()
				.set(detailedOrder.getOrderGeneralInfo().getMartialStatus());
		detailedOrderDto.getOrderGeneralInfoDto().getSecondNameInLatin()
				.set(detailedOrder.getOrderGeneralInfo().getSecondNameInLatin());
		if (detailedOrder.getOrderGeneralInfo().getSex() != null) {
			detailedOrderDto.getOrderGeneralInfoDto().getSex().set(detailedOrder.getOrderGeneralInfo().getSex().name());
		}
		detailedOrderDto.getOrderGeneralInfoDto().getStartDocsProviding()
				.set(detailedOrder.getOrderGeneralInfo().getStartDocsProviding());
		detailedOrderDto.getOrderGeneralInfoDto().getVisaType().set(detailedOrder.getOrderGeneralInfo().getVisaType());

		detailedOrderDto.getOrderPassportDataDto().getForingPassportExpDate()
				.set(detailedOrder.getOrderPassportData().getForingPassportExpDate());
		detailedOrderDto.getOrderPassportDataDto().getForingPassportIssuedBy()
				.set(detailedOrder.getOrderPassportData().getForingPassportIssuedBy());
		detailedOrderDto.getOrderPassportDataDto().getForingPassportNumber()
				.set(detailedOrder.getOrderPassportData().getForingPassportNumber());
		detailedOrderDto.getOrderPassportDataDto().getForingPassportStartDate()
				.set(detailedOrder.getOrderPassportData().getForingPassportStartDate());
		detailedOrderDto.getOrderPassportDataDto().getSocialId()
				.set(detailedOrder.getOrderPassportData().getSocialId());

		detailedOrderDto.getTravelPurpose().set(detailedOrder.getTravelPurpose());
		if (detailedOrder.getSuitableInOutDates() != null) {
			detailedOrderDto.getSuitableInOutDatesDto().getFrom()
					.set(detailedOrder.getSuitableInOutDates().getFromDate());
			detailedOrderDto.getSuitableInOutDatesDto().getTo().set(detailedOrder.getSuitableInOutDates().getToDate());
		}
		// TODO: add enriching of previous visas date
	}

	public static ObservableList<String> getProcessStatuses() {
		return FXCollections.observableArrayList(Arrays.asList(ProcessingStatus.values()).stream()
				.map(role -> role.toString()).collect(Collectors.toList()));
	}

	public static ObservableList<DetailedOrderDto> convertToDetailedOrderDto(List<DetailedOrder> list) {
		ObservableList<DetailedOrderDto> result = FXCollections.observableArrayList();
		list.forEach(e -> result.add(new DetailedOrderDto(e)));
		return result;
	}
}
