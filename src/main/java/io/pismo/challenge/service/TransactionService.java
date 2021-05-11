package io.pismo.challenge.service;

import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.pismo.challenge.bean.TransactionRequestDTO;
import io.pismo.challenge.bean.TransactionResponseDTO;
import io.pismo.challenge.model.Transaction;
import io.pismo.challenge.repository.TransactionRepository;

@ApplicationScoped
public class TransactionService {

	@Inject
	TransactionRepository transactionRepository;

	@Transactional(REQUIRED)
	public TransactionResponseDTO create(TransactionRequestDTO dto) {
		var entity = Transaction.builder().build();
		return transactionRepository.persist(entity).toResponse();
	}

}
