package com.mmm.greenway.data.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.OrderType;
import com.mmm.greenway.entity.ProcessingStatus;

public interface DetailedOrderRepository extends CrudRepository<DetailedOrder, Long> {
	List<DetailedOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndOrderTypeOrderByDateDesc(
			String clientName, String phoneNumber, OrderType orderType);

	List<DetailedOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndRegistrationNotInOrderByDateDesc(
			String clientName, String phoneNumber, Collection<ProcessingStatus> processingStatus);

	@Query("select dor from DetailedOrder dor inner join dor.invitationDocument ind where upper(dor.clientName) like :clientName and dor.phoneNumber like :phoneNumber and ind.processingStatus not in (:processingStatus) order by dor.date")
	List<DetailedOrder> findActiveForInvitator(@Param("clientName") String clientName,
			@Param("phoneNumber") String phoneNumber,
			@Param("processingStatus") Collection<ProcessingStatus> processingStatus);
}
