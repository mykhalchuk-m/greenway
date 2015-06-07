package com.mmm.grenway.javafx.service;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.BaseOrderRepository;
import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.BaseOrder;

import static com.mmm.greenway.entity.specification.BaseOrderSpecification.*;

import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.service.converter.BaseOrderConverter;

@Service
public class BaseOrderService {
	@Autowired
	private BaseOrderRepository baseOrderRepository;
	@Autowired
	private UserRepository userRepository;

	public BaseOrderDto findById(Long id) {
		BaseOrder baseOrder = baseOrderRepository.findOne(id);
		if (baseOrder != null) {
			return new BaseOrderDto(baseOrder);
		} else {
			return null;
		}
	}

	public ObservableList<BaseOrderDto> findOrderDetails(BaseOrderFilterDto filterDto) {
		return BaseOrderConverter.convertToOrderDetailDto(baseOrderRepository.findAll(byClientNamePhoneAndOperatorLocation(filterDto
				.getClientNameFilter().get(), filterDto.getPhoneNumberFilter().get(), filterDto.getLocationFilter()
				.get()), 100, new Sort(Sort.Direction.DESC, "date")));
	}

	public void save(BaseOrderDto baseOrderDto) {
		baseOrderDto.setOperator(new SimpleStringProperty(((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername()));
		baseOrderRepository.save(BaseOrderConverter.toOrderDetail(baseOrderDto, userRepository));
	}
}
