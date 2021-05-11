package io.pismo.challenge.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

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
@Schema(description = "Object that represents a single result of a transaction.")
public class TransactionResponseDTO implements Serializable {

	@NotNull
	@Positive
	@JsonProperty("id")
	@Schema(name = "id", description = "Identifier of the transaction.", example = "1")
	private Long id;

	@NotNull
	@Positive
	@JsonProperty("account")
	@Schema(name = "account", description = "A positive number that identifies an account.", example = "1")
	private Long account;

	@NotNull
	@Positive
	@JsonProperty("operation_type")
	@Schema(name = "operation_type", description = "A positive number that identifies an operation. It must be one of the following:<br><ol>"
			+ "<li>Cash purchase</li><li>Installment purchase</li><li>Withdraw</li><li>Payment</li></ol>", example = "1")
	private Long operationType;

	@NotNull
	@JsonProperty("amount")
	@Schema(name = "amount", description = "The transaction amount. A negative value indicates that the operation type is a debit, otherwise a credit.",
			example = "-60.22")
	private BigDecimal amount;

	@NotNull
	@JsonProperty("event_date")
	@Schema(name = "event_date", description = "The transaction event timestamp, based on America/Sao_Paulo timezone.", example = "2021-05-11T14:52:53.382")
	private LocalDateTime eventDate;

}
