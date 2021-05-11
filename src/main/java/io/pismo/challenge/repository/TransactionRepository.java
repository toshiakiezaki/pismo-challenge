package io.pismo.challenge.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import io.pismo.challenge.model.Transaction;

@ApplicationScoped
public class TransactionRepository {

	@Inject
	EntityManager entityManager;

	public Transaction persist(Transaction entity) {
		entityManager.persist(entity);
		return entity;
	}
}
