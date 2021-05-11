package io.pismo.challenge.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Schema(description = "Object that represents an account creation request.")
public class AccountRequestDTO implements Serializable {

	@NotNull
	@JsonProperty("document_number")
	@Pattern(regexp = "^([0-9]{11}|[0-9]{14})$")
	@Schema(name = "document_number", description = "Document number that identifies a person or a company on the Government system. It must contain 11 "
			+ "characters for a person and 14 for a company.", example = "41338592009", pattern = "^([0-9]{11}|[0-9]{14})$")
	private String documentNumber;

}
