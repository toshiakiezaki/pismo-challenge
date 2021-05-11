package io.pismo.challenge.exception;

import javax.ws.rs.core.Response.Status;

import io.pismo.challenge.bean.ErrorResponseDTO;

public class BusinessException extends RuntimeException {

	private static final String BUSINESS_KIND = "BUSINESS";

	private final Status status;
	private final String path;
	private final String message;
	private final Object value;

	public BusinessException(String message) {
		this(Status.BAD_REQUEST, null, message, null, null);
	}

	public BusinessException(Status status, String message) {
		this(status, null, message, null, null);
	}

	public BusinessException(Status status, String message, Object value) {
		this(status, null, message, value, null);
	}

	public BusinessException(Status status, String path, String message, Object value, Throwable cause) {
		super(message, cause);
		this.status = status;
		this.path = path;
		this.message = message;
		this.value = value;
	}

	public Status getStatus() {
		return status;
	}

	public ErrorResponseDTO toResponse() {
		return ErrorResponseDTO.builder().kind(BUSINESS_KIND).path(path).message(message).value(value).build();
	}

}
