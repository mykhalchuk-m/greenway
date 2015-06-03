package com.mmm.grenway.javafx.service;

import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.BaseOrderRepository;
import com.mmm.greenway.data.repository.DetailedOrderRepository;
import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.OrderType;
import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;
import com.mmm.grenway.javafx.service.converter.DetailedOrderConverter;

@Service
public class DetailedOrderService {
	@Autowired
	private DetailedOrderRepository detailedOrderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BaseOrderRepository baseOrderRepository;
	@Autowired
	private BaseOrderService baseOrderService;

	public DetailedOrderDto findById(Long id) {
		return new DetailedOrderDto(detailedOrderRepository.findOne(id));
	}

	public void findById(Long id, DetailedOrderDto detailedOrderDto) {
		DetailedOrder detailedOrder = detailedOrderRepository.findOne(id);
		if (detailedOrder == null) {
			BaseOrder baseOrder = baseOrderRepository.findOne(id);
			DetailedOrderConverter.enrichWithBaseOrder(detailedOrderDto, baseOrder);
		} else {
			DetailedOrderConverter.enrichDtoWithEntity(detailedOrderDto, detailedOrderRepository.findOne(id));
		}
	}

	public void save(DetailedOrderDto detailedOrderDto) {
		DetailedOrder detailedOrder = DetailedOrderConverter.toDetailedOrder(detailedOrderDto);
		detailedOrder.setOperator(userRepository.findOne(detailedOrderDto.getOperator().get()));
		detailedOrder.getDocumentPerOrders().forEach(e -> e.setOrder(detailedOrder));
		detailedOrderRepository.save(detailedOrder);
	}

	public ObservableList<DetailedOrderDto> findDetailedOrdersRegisterd(BaseOrderFilterDto baseOrderFilterDto) {
		List<DetailedOrder> detailedOrders = detailedOrderRepository
				.findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndOrderTypeAndIsDoneFalseOrderByDateDesc(
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

	public ObservableList<DetailedOrderDto> findDetailedOrdersForInvitator(BaseOrderFilterDto baseOrderFilterDto) {
		List<DetailedOrder> detailedOrders = detailedOrderRepository.findActiveForInvitator("%"
				+ baseOrderFilterDto.getClientNameFilter().get() + "%", "%"
				+ baseOrderFilterDto.getPhoneNumberFilter().get() + "%",
				Arrays.asList(ProcessingStatus.NONE, ProcessingStatus.DONE));
		return DetailedOrderConverter.convertToDetailedOrderDto(detailedOrders);
	}
}
