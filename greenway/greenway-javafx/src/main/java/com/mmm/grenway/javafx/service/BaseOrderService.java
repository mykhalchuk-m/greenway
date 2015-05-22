package com.mmm.grenway.javafx.service;

import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.BaseOrderRepository;
import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.BaseOrder;
import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.service.converter.BaseOrderConverter;

@Service
public class BaseOrderService {
	@Autowired
	private BaseOrderRepository baseOrderRepository;
	@Autowired
	private UserRepository userRepository;

	public ObservableList<BaseOrderDto> findOrderDetails(BaseOrderFilterDto filterDto) {
		return BaseOrderConverter.convertToOrderDetailDto(baseOrderRepository
				.findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingOrderByDateDesc(filterDto
						.getClientNameFilter().get().toUpperCase(), filterDto.getPhoneNumberFilter().get()));
	}

	public BaseOrder save(BaseOrder baseOrder) {
		baseOrder.setOperator(userRepository.findOne(((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername()));
		return baseOrderRepository.save(baseOrder);
	}

	public void save(BaseOrderDto orderDetailDto) {
		baseOrderRepository.save(BaseOrderConverter.toOrderDetail(orderDetailDto, userRepository));
	}
}
