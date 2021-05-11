package io.pismo.challenge.service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.pismo.challenge.bean.AccountRequestDTO;
import io.pismo.challenge.bean.AccountResponseDTO;
import io.pismo.challenge.domain.DocumentType;
import io.pismo.challenge.exception.AccountNotFoundException;
import io.pismo.challenge.model.Account;
import io.pismo.challenge.repository.AccountRepository;

@ApplicationScoped
public class AccountService {

	@Inject
	AccountRepository accountRepository;

	@Transactional(REQUIRED)
	public AccountResponseDTO create(AccountRequestDTO dto) {
		var entity = Account.builder().documentType(DocumentType.from(dto.getDocumentNumber())).documentNumber(dto.getDocumentNumber())
				.build();
		return accountRepository.persist(entity).toResponse();
	}

	@Transactional(SUPPORTS)
	public AccountResponseDTO findById(Long id) {
		return accountRepository.findById(id).map(Account::toResponse).orElseThrow(() -> new AccountNotFoundException(id));
	}

}
