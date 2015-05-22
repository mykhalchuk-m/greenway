package com.mmm.grenway.javafx.service;

import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.DetailedOrderRepository;
import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.OrderType;
import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
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
		detailedOrder.getDocumentPerOrders().forEach(e -> e.setOrder(detailedOrder));
		detailedOrderRepository.save(detailedOrder);
	}

	public ObservableList<InvitaitionOrderDto> findAllForInvitationProcess() {
		return InvitationOrderConverter.toInvitaitionOrderDtoList(detailedOrderRepository.findAll());
	}

	public ObservableList<DetailedOrderDto> findDetailedOrdersRegisterd(BaseOrderFilterDto baseOrderFilterDto) {
		List<DetailedOrder> detailedOrders = detailedOrderRepository
				.findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndOrderTypeOrderByDateDesc(
						baseOrderFilterDto.getClientNameFilter().get(),
						baseOrderFilterDto.getPhoneNumberFilter().get(), OrderType.REGISTER);
		return DetailedOrderConverter.convertToDetailedOrderDto(detailedOrders);
	}

	public ObservableList<DetailedOrderDto> findDetailedOrdersForRegistrator(BaseOrderFilterDto baseOrderFilterDto) {
		List<DetailedOrder> detailedOrders = detailedOrderRepository
				.findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndRegistrationNotInOrderByDateDesc(
						baseOrderFilterDto.getClientNameFilter().get(),
						baseOrderFilterDto.getPhoneNumberFilter().get(),
						Arrays.asList(ProcessingStatus.NONE, ProcessingStatus.DONE));
		return DetailedOrderConverter.convertToDetailedOrderDto(detailedOrders);
	}
}
