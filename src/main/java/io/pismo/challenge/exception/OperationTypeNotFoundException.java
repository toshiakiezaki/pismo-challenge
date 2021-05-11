package io.pismo.challenge.exception;

import javax.ws.rs.core.Response;

public class OperationTypeNotFoundException extends BusinessException {

	public static final String MESSAGE_FORMAT = "operation type not found with id %d";

	public OperationTypeNotFoundException(Long id, String path) {
		super(Response.Status.NOT_FOUND, path, String.format(MESSAGE_FORMAT, id), id, null);
	}

}
