package com.mmm.greenway.data.repository.custom;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class LimitableJpaRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends
		JpaRepositoryFactoryBean<R, T, ID> {
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new LimitableJpaRepositoryFactory<T, Serializable>(entityManager);
	}

	private static class LimitableJpaRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

		private EntityManager entityManager;

		public LimitableJpaRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@Override
		@SuppressWarnings("unchecked")
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new LimitableJpaRepositoryImpl<T, Serializable>((Class<T>) metadata.getDomainType(), entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return LimitableJpaRepositoryImpl.class;
		}
	}
}
