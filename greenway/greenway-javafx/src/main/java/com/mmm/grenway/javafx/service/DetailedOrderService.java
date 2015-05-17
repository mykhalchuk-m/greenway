package com.mmm.grenway.javafx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.DetailedOrderRepository;
import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;
import com.mmm.grenway.javafx.service.converter.DetailedOrderConverter;

@Service
public class DetailedOrderService {
	@Autowired
	private DetailedOrderRepository detailedOrderRepository;
	@Autowired
	private UserRepository userRepository;

	public void save(DetailedOrderDto detailedOrderDto) {
		DetailedOrder detailedOrder = DetailedOrderConverter.toDetailedOrder(detailedOrderDto);
		detailedOrder.setOperator(userRepository.findOne(detailedOrderDto.getOperator().get()));
		detailedOrderRepository.save(detailedOrder);
	}
}
