package io.pismo.challenge.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Schema(description = "Object that represents a single result of an account.")
public class AccountResponseDTO implements Serializable {

	@NotNull
	@Positive
	@JsonProperty("id")
	@Schema(name = "id", description = "Identifier of the account.", example = "1")
	private Long id;

	@NotNull
	@Positive
	@JsonProperty("document_type")
	@Schema(name = "document_type", description = "Document type identifies if the provided number is a person (1) or a company (2).", example = "1")
	private Long documentType;

	@NotNull
	@JsonProperty("document_number")
	@Pattern(regexp = "^([0-9]{11}|[0-9]{14})$")
	@Schema(name = "document_number", description = "Document number that identifies a person or a company on the Government system. It must contain 11 "
			+ "characters for a person and 14 for a company.", example = "41338592009", pattern = "^([0-9]{11}|[0-9]{14})$")
	private String documentNumber;

}
