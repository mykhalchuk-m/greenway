package com.mmm.grenway.javafx.service.converter;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.OrderType;
import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;

public class BaseOrderConverter {
	public static ObservableList<BaseOrderDto> convertToOrderDetailDto(Iterable<BaseOrder> orderDetails) {
		ObservableList<BaseOrderDto> result = FXCollections.observableArrayList();
		orderDetails.forEach(e -> result.add(new BaseOrderDto(e)));
		return result;
	}

	public static BaseOrder toOrderDetail(BaseOrderDto orderDetailDto, UserRepository userRepository) {
		BaseOrder orderDetail = new BaseOrder();
		orderDetail.setClientName(orderDetailDto.getClientName().get());
		orderDetail.setDate(LocalDateTime.now());
		orderDetail.setNote(orderDetailDto.getNote().get());
		orderDetail.setOperator(userRepository.findOne(orderDetailDto.getOperator().get()));
		orderDetail.setPhoneNumber(orderDetailDto.getPhoneNumber().get());
		orderDetail.setSupplierName(orderDetailDto.getSupplierName().get());
		String orderType = orderDetailDto.getOrderType().get();
		orderDetail.setOrderType(orderType == null ? OrderType.CONSULT : OrderType.valueOf(orderType));
		return orderDetail;
	}

	public static void toBaseOrderDto(BaseOrder baseOrder, BaseOrderDto baseOrderDto) {
		baseOrderDto.getSupplierName().set(baseOrder.getSupplierName());
		baseOrderDto.getClientName().set(baseOrder.getClientName());
		baseOrderDto.getPhoneNumber().set(baseOrder.getPhoneNumber());
		baseOrderDto.getNote().set(baseOrder.getNote());
	}

	public static BaseOrderDto getBaseOrderDto(DetailedOrderDto detailedOrderDto) {
		BaseOrderDto baseOrderDto = new BaseOrderDto();
		baseOrderDto.getSupplierName().set(detailedOrderDto.getSupplierName().get());
		baseOrderDto.getClientName().set(detailedOrderDto.getClientName().get());
		baseOrderDto.getPhoneNumber().set(detailedOrderDto.getPhoneNumber().get());
		baseOrderDto.getNote().set(detailedOrderDto.getNote().get());
		baseOrderDto.getOperator().set(detailedOrderDto.getOperator().get());
		baseOrderDto.getDate().set(detailedOrderDto.getDate().get());
		baseOrderDto.getOrderType().set(detailedOrderDto.getOrderType().get());
		return baseOrderDto;
	}
}
