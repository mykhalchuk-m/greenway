package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.grenway.javafx.dto.InvitaitionOrderDto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvitationOrderConverter {
	public static ObservableList<InvitaitionOrderDto> toInvitaitionOrderDtoList(Iterable<DetailedOrder> detailedOrders) {
		ObservableList<InvitaitionOrderDto> result = FXCollections.observableArrayList();
		detailedOrders.forEach(o -> result.add(new InvitaitionOrderDto(o)));
		return result;
	}
}
