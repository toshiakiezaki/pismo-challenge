package io.pismo.challenge.repository;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import io.pismo.challenge.model.OperationType;

@ApplicationScoped
public class OperationTypeRepository {

	@Inject
	EntityManager entityManager;

	public Optional<OperationType> findById(Long id) {
		var query = entityManager.createQuery("FROM OperationType ot WHERE ot.id = :id");
		query.setParameter("id", id);
		try {
			return Optional.of((OperationType) query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

}
