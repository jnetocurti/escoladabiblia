package br.com.escoladabiblia.repository.custom;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AbstractRepositoryImpl<T, ID extends Serializable> {

	@PersistenceContext
	private final EntityManager entityManager;

	public AbstractRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	TypedQuery<T> createTypedQuery(String query, Class<T> clazz) {
		return this.entityManager.createQuery(query, clazz);
	}

	Query createQuery(String query) {
		return this.entityManager.createQuery(query);
	}

}
