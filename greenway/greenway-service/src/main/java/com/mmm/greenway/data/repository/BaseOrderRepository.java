package com.mmm.greenway.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mmm.greenway.data.repository.custom.LimitableJpaRepository;
import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.OrderType;

public interface BaseOrderRepository extends LimitableJpaRepository<BaseOrder, Long>, JpaSpecificationExecutor<BaseOrder> {
//	List<BaseOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingOrderByDateDesc(String clientName,
//			String phoneNumber);
	
	List<BaseOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndOrderTypeOrderByDateDesc(String clientName,
			String phoneNumber, OrderType orderType);
}
