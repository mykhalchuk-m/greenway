package com.mmm.grenway.javafx.service;

import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.OrderDetailRepository;
import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.BaseOrder;
import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.service.converter.OrderDetailConverter;

@Service
public class BaseOrderService {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private UserRepository userRepository;

	public ObservableList<BaseOrderDto> findOrderDetails() {
		return OrderDetailConverter.convertToOrderDetailDto(orderDetailRepository.findAll());
	}

	public BaseOrder save(BaseOrder baseOrder) {
		baseOrder.setOperator(userRepository.findOne(((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername()));
		return orderDetailRepository.save(baseOrder);
	}

	public void save(BaseOrderDto orderDetailDto) {
		orderDetailRepository.save(OrderDetailConverter.toOrderDetail(orderDetailDto, userRepository));
	}
}
