package com.mmm.greenway.data.repository.custom;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LimitableJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	List<T> findAll(Specification<T> spec, int limit, Sort sort);

	List<T> findAll(Specification<T> spec, int limit);
}
