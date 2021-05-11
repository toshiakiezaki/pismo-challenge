package io.pismo.challenge.repository;

import javax.enterprise.context.ApplicationScoped;

import io.pismo.challenge.model.OperationType;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class OperationTypeRepository implements PanacheRepositoryBase<OperationType, Long> {
}
