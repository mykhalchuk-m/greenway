package com.mmm.grenway.javafx.service.converter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.BaseOrder;
import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.util.DateUtil;

public class OrderDetailConverter {
	public static ObservableList<BaseOrderDto> convertToOrderDetailDto(Iterable<BaseOrder> orderDetails) {
		ObservableList<BaseOrderDto> result = FXCollections.observableArrayList();
		orderDetails.forEach(e -> result.add(new BaseOrderDto(e)));
		return result;
	}

	public static BaseOrder toOrderDetail(BaseOrderDto orderDetailDto, UserRepository userRepository) {
		BaseOrder orderDetail = new BaseOrder();
		orderDetail.setClientName(orderDetailDto.getClientNameProp().get());
		orderDetail.setDate(DateUtil.parse(orderDetailDto.getDateProp().get()));
		orderDetail.setNote(orderDetailDto.getNoteProp().get());
		orderDetail.setOperator(userRepository.findOne(orderDetailDto.getOperatorProp().get()));
		orderDetail.setPhoneNumber(orderDetailDto.getClientPhoneProp().get());
		orderDetail.setSupplierName(orderDetailDto.getSupplierProp().get());
		return orderDetail;
	}
}
