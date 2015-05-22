package com.mmm.greenway.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.OrderType;

public interface BaseOrderRepository extends CrudRepository<BaseOrder, Long> {
	List<BaseOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingOrderByDateDesc(String clientName,
			String phoneNumber);
	
	List<BaseOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndOrderTypeOrderByDateDesc(String clientName,
			String phoneNumber, OrderType orderType);
}
