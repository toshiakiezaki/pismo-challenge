package io.pismo.challenge.service;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.time.LocalDateTime;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.pismo.challenge.bean.TransactionRequestDTO;
import io.pismo.challenge.bean.TransactionResponseDTO;
import io.pismo.challenge.exception.AccountNotFoundException;
import io.pismo.challenge.exception.OperationTypeNotFoundException;
import io.pismo.challenge.model.Transaction;
import io.pismo.challenge.repository.AccountRepository;
import io.pismo.challenge.repository.OperationTypeRepository;
import io.pismo.challenge.repository.TransactionRepository;

@ApplicationScoped
public class TransactionService {

	@Inject
	AccountRepository accountRepository;

	@Inject
	OperationTypeRepository operationTypeRepository;

	@Inject
	TransactionRepository transactionRepository;

	@Transactional(REQUIRED)
	public TransactionResponseDTO create(TransactionRequestDTO dto) {
		var account = accountRepository.findById(dto.getAccount()).orElseThrow(() -> new AccountNotFoundException(dto.getAccount(), "account"));
		var operationType = operationTypeRepository.findById(dto.getOperationType())
				.orElseThrow(() -> new OperationTypeNotFoundException(dto.getOperationType(), "operationType"));
		var entity = Transaction.builder().account(account).operationType(operationType)
				.amount(operationType.isCredit() ? dto.getAmount() : dto.getAmount().negate()).eventDate(LocalDateTime.now()).build();
		return transactionRepository.persist(entity).toResponse();
	}

}
