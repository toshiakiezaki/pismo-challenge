package io.pismo.challenge.domain;

import static java.util.Arrays.stream;
import static java.util.Objects.isNull;

import io.pismo.challenge.exception.DocumentNumberFormatException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DocumentType {

	INDIVIDUAL(1, 11),
	LEGAL_ENTITY(2, 14);

	private final long code;
	private final int documentLength;

	public long code() {
		return code;
	}

	public static DocumentType from(String documentNumber) {
		if (isNull(documentNumber)) {
			return null;
		}
		return stream(values()).filter(type -> type.documentLength == documentNumber.length()).findAny()
				.orElseThrow(() -> new DocumentNumberFormatException(documentNumber));
	}

}
