package io.pismo.challenge.repository;

import javax.enterprise.context.ApplicationScoped;

import io.pismo.challenge.model.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class AccountRepository implements PanacheRepositoryBase<Account, Long> {
}
