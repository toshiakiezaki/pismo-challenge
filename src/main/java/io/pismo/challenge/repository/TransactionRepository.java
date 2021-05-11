package io.pismo.challenge.repository;

import javax.enterprise.context.ApplicationScoped;

import io.pismo.challenge.model.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<Transaction, Long> {
}
