package io.pismo.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.pismo.challenge.bean.AccountRequestDTO;
import io.pismo.challenge.domain.DocumentType;
import io.pismo.challenge.exception.AccountNotFoundException;
import io.pismo.challenge.exception.DocumentNumberFormatException;
import io.pismo.challenge.model.Account;
import io.pismo.challenge.repository.AccountRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class AccountServiceTest {

	@Inject
	AccountService service;

	@InjectMock
	AccountRepository repository;

	@Test
	public void create() {
		when(repository.persist(any(Account.class))).thenAnswer((Answer<Account>) invocationOnMock -> {
			var account = invocationOnMock.getArgument(0, Account.class);
			account.setId(1L);
			return account;
		});

		var actual = service.create(AccountRequestDTO.builder().documentNumber("12345678901").build());
		assertSoftly(softly -> {
			softly.assertThat(actual.getId()).isEqualTo(1L);
			softly.assertThat(actual.getDocumentNumber()).isEqualTo("12345678901");
			softly.assertThat(actual.getDocumentType()).isEqualTo(DocumentType.INDIVIDUAL.code());
			softly.assertThat(actual.getAvailableCredit()).isEqualTo(BigDecimal.ZERO);
		});
	}

	@Test
	public void createWithInvalidDocumentNumber() {
		assertThatThrownBy(() -> service.create(AccountRequestDTO.builder().documentNumber("123456789").build()))
				.isExactlyInstanceOf(DocumentNumberFormatException.class);
	}

	@Test
	public void findById() {
		when(repository.findById(1L))
				.thenReturn(Optional.of(Account.builder().id(1L).documentType(DocumentType.INDIVIDUAL).documentNumber("12345678901").build()));

		var actual = service.findById(1L);
		assertThat(actual.getId()).isEqualTo(1L);
	}

	@Test
	public void findByIdWithAccountNotFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.findById(1L)).isExactlyInstanceOf(AccountNotFoundException.class);
	}

}