package io.pismo.challenge.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TransactionRequestDTO implements Serializable {

	@NotNull
	@Positive
	@JsonProperty("account")
	private Long account;

	@NotNull
	@Positive
	@JsonProperty("operation_type")
	private Long operationType;

	@NotNull
	@Positive
	@JsonProperty("amount")
	private BigDecimal amount;

}
