package io.pismo.challenge.exception;

public class InsufficientCreditException extends BusinessException {

	private static final String MESSAGE_FORMAT = "insufficient credit for operation on account %d";

	public InsufficientCreditException(Long accountId) {
		super(String.format(MESSAGE_FORMAT, accountId));
	}

}
