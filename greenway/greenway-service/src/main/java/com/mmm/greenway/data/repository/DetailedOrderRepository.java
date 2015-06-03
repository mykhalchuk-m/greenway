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
	/**
	 * Select list of DetailedOrder elements for documnentoloh by next
	 * parameters
	 * 
	 * @param clientName
	 *            name of client who made the order
	 * @param phoneNumber
	 *            phone number of client who made the order
	 * @param orderType
	 *            for documentoloh OrderType.REGISTERED is required
	 * */
	List<DetailedOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndOrderTypeAndIsDoneFalseOrderByDateDesc(
			String clientName, String phoneNumber, OrderType orderType);

	/**
	 * Select list of DetailedOrder elements for registrator by next parameters
	 * 
	 * @param clientName
	 *            name of client who made the order
	 * @param phoneNumber
	 *            phone number of client who made the order
	 * @param processingStatus
	 *            for registrator processingStatus should not be
	 *            ProcessingStatus.NONE and ProcessingStatus.DONE. Main purpose
	 *            of such condition is that registrator only needs active
	 *            assigned to him orders.
	 * */
	List<DetailedOrder> findFirst100ByClientNameIgnoreCaseContainingAndPhoneNumberContainingAndRegistrationNotInOrderByDateDesc(
			String clientName, String phoneNumber, Collection<ProcessingStatus> processingStatus);

	@Query("select dor from DetailedOrder dor inner join dor.invitationDocument ind where upper(dor.clientName) like :clientName and dor.phoneNumber like :phoneNumber and ind.processingStatus not in (:processingStatus) order by dor.date desc")
	List<DetailedOrder> findActiveForInvitator(@Param("clientName") String clientName,
			@Param("phoneNumber") String phoneNumber,
			@Param("processingStatus") Collection<ProcessingStatus> processingStatus);
}
