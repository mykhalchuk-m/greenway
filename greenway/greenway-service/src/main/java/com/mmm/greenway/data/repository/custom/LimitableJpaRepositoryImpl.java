package com.mmm.greenway.data.repository.custom;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class LimitableJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
		LimitableJpaRepository<T, ID> {

	public LimitableJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Override
	public List<T> findAll(Specification<T> spec, int limit, Sort sort) {
		if (limit < 0) {
			throw new IllegalArgumentException("Limit can't be less then one");
		}
		
		TypedQuery<T> query = getQuery(spec, sort);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}

	@Override
	public List<T> findAll(Specification<T> spec, int limit) {
		return findAll(spec, limit, null);
	}

}
