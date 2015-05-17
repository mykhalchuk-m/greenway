package com.mmm.greenway.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mmm.greenway.entity.BaseOrder;

public interface BaseOrderRepository extends CrudRepository<BaseOrder, Long> {
	@Query("select bo from BaseOrder bo where upper(bo.clientName) like :clientName% and bo.phoneNumber like %:phoneNumber%")
	List<BaseOrder> findFilteredBaseOrder(@Param("clientName") String clientName,
			@Param("phoneNumber") String phoneNumber);
}
