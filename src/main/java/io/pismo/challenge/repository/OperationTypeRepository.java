package io.pismo.challenge.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class OperationTypeRepository {

	@Inject
	EntityManager entityManager;

}
