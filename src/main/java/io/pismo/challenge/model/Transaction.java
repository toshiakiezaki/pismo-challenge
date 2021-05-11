package io.pismo.challenge.model;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.pismo.challenge.bean.TransactionResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Transaction implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private OperationType operationType;

	@Column(nullable = false, updatable = false)
	private BigDecimal amount;

	@Column(nullable = false, updatable = false)
	private LocalDateTime eventDate;

	public TransactionResponseDTO toResponse() {
		return TransactionResponseDTO.builder().id(id).account(account.getId()).operationType(operationType.getId()).amount(amount).eventDate(eventDate)
				.build();
	}
}
