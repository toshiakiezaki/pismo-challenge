package io.pismo.challenge.exception;

public class DocumentNumberFormatException extends BusinessException {

	private static final String MESSAGE_FORMAT = "invalid document number format for %s";

	public DocumentNumberFormatException(String documentNumber) {
		super(String.format(MESSAGE_FORMAT, documentNumber));
	}

}
