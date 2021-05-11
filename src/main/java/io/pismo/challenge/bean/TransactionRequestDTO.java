package io.pismo.challenge.bean;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Schema(description = "Object that represents a transaction request.")
public class TransactionRequestDTO implements Serializable {

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
	@Positive
	@JsonProperty("amount")
	@Schema(name = "amount", description = "A positive floating number that will be credited or debited to the account. The service will verify the"
			+ " operation associated to this transaction and will perform a credit or a debit based on this information.", example = "60.22")
	private BigDecimal amount;

}
