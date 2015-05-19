package com.mmm.greenway.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mmm.greenway.entity.BaseOrder;

public interface BaseOrderRepository extends CrudRepository<BaseOrder, Long> {
	List<BaseOrder> findFirst100ByClientNameContainingAndPhoneNumberContainingOrderByDateDesc(String clientName,
			String phoneNumber);
}
