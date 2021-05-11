package io.pismo.challenge.repository;

import java.util.Optional;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import io.pismo.challenge.model.Account;

@ApplicationScoped
public class AccountRepository {

	@Inject
	EntityManager entityManager;

	public Optional<Account> findById(Long id) {
		var query = entityManager.createQuery("FROM Account a WHERE a.id = :id");
		query.setParameter("id", id);
		try {
			return Optional.of((Account) query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public Account persist(Account account) {
		entityManager.persist(account);
		return account;
	}

}
