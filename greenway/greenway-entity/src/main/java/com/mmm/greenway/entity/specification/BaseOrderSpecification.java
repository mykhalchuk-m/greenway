package com.mmm.greenway.entity.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.BaseOrder_;
import com.mmm.greenway.entity.User_;

public class BaseOrderSpecification {
	public static Specification<BaseOrder> byClientNamePhoneAndOperatorLocation(String clientName, String phoneNumber,
			String location) {
		return new Specification<BaseOrder>() {

			@Override
			public Predicate toPredicate(Root<BaseOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate result = null;
				if (clientName != null && !clientName.isEmpty()) {
					result = cb.like(root.get(BaseOrder_.clientName),
							new StringBuilder("%").append(clientName).append("%").toString());
				}
				if (phoneNumber != null && !phoneNumber.isEmpty()) {
					Predicate phoneNumberLike = cb.like(root.get(BaseOrder_.phoneNumber), new StringBuilder("%")
							.append(phoneNumber).append("%").toString());
					if (result == null) {
						result = phoneNumberLike;
					} else {
						result = cb.and(result, phoneNumberLike);
					}
				}
				if (location != null && !location.isEmpty()) {
					Predicate locationEquals = cb.equal(root.get(BaseOrder_.operator).get(User_.location), location);
					if (result == null) {
						result = locationEquals;
					} else {
						result = cb.and(result, locationEquals);
					}
				}

				return result;
			}
		};
	}

}
