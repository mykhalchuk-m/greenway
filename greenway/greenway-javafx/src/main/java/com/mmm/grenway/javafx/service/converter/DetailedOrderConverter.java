package com.mmm.grenway.javafx.service.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.DocumentPerOrder;
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
		List<DocumentPerOrder> documentsPerOrder = new ArrayList<>();
		detailedOrderDto.getDocumentPerOrder().forEach(
				e -> {
					DocumentPerOrder documentPerOrder = new DocumentPerOrder();
					documentPerOrder.setProcessingStatus(!e.getProcessingStatus().get().isEmpty() ? ProcessingStatus
							.valueOf(e.getProcessingStatus().get()) : ProcessingStatus.NONE);
					documentPerOrder.setDocument(DocumentConverter.toDocument(e.getDocument()));
					documentsPerOrder.add(documentPerOrder);
				});
		detailedOrder.setDocumentPerOrders(documentsPerOrder);
		if (detailedOrderDto.getInvitationDocument().getDocument().getName().get() != null
				&& !detailedOrderDto.getInvitationDocument().getDocument().getName().get().isEmpty()) {
			DocumentPerOrder invitationDocument = new DocumentPerOrder();
			invitationDocument.setDocument(DocumentConverter.toDocument(detailedOrderDto.getInvitationDocument()
					.getDocument()));
			invitationDocument.setOrder(detailedOrder);
			String invitationDocumentProcessStatus = detailedOrderDto.getInvitationDocument().getProcessingStatus()
					.get();
			invitationDocument.setProcessingStatus(invitationDocumentProcessStatus != null
					&& !invitationDocumentProcessStatus.isEmpty() ? ProcessingStatus
					.valueOf(invitationDocumentProcessStatus) : ProcessingStatus.NONE);
			detailedOrder.setInvitationDocument(invitationDocument);
		}
		if (detailedOrderDto.getRegistrationDate().get() != null) {
			detailedOrder.setRegistrationDate(detailedOrderDto.getRegistrationDate().get());
		}
		return detailedOrder;
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
