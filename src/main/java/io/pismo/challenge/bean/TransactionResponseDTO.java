package io.pismo.challenge.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class TransactionResponseDTO implements Serializable {

	@NotNull
	@Positive
	@JsonProperty("id")
	private Long id;

	@NotNull
	@Positive
	@JsonProperty("account")
	private Long account;

	@NotNull
	@Positive
	@JsonProperty("operation_type")
	private Long operationType;

	@NotNull
	@JsonProperty("amount")
	private BigDecimal amount;

	@NotNull
	@JsonProperty("event_date")
	private LocalDateTime eventDate;

}
