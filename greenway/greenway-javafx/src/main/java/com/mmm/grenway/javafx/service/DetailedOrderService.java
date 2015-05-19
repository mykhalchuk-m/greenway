package com.mmm.grenway.javafx.service;

import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.DetailedOrderRepository;
import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;
import com.mmm.grenway.javafx.dto.InvitaitionOrderDto;
import com.mmm.grenway.javafx.service.converter.DetailedOrderConverter;
import com.mmm.grenway.javafx.service.converter.InvitationOrderConverter;

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
	
	public ObservableList<InvitaitionOrderDto> findAllForInvitationProcess() {
		return InvitationOrderConverter.toInvitaitionOrderDtoList(detailedOrderRepository.findAll());
	}
}
