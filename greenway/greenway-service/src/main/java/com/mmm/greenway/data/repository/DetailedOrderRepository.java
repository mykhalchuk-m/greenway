package com.mmm.greenway.data.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.OrderType;
import com.mmm.greenway.entity.ProcessingStatus;

public interface DetailedOrderRepository extends CrudRepository<DetailedOrder, Long> {
	List<DetailedOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndOrderTypeOrderByDateDesc(
			String clientName, String phoneNumber, OrderType orderType);
	
	List<DetailedOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndRegistrationNotInOrderByDateDesc(
			String clientName, String phoneNumber, Collection<ProcessingStatus> orderTypes);
}
