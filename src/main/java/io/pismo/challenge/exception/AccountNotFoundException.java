package io.pismo.challenge.exception;

import javax.ws.rs.core.Response;

public class AccountNotFoundException extends BusinessException {

	public static final String MESSAGE_FORMAT = "account not found with id %d";

	public AccountNotFoundException(Long id) {
		super(Response.Status.NOT_FOUND, String.format(MESSAGE_FORMAT, id), id);
	}

	public AccountNotFoundException(Long id, String path) {
		super(Response.Status.NOT_FOUND, path, String.format(MESSAGE_FORMAT, id), id, null);
	}

}
