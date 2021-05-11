package io.pismo.challenge.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
public class AccountResponseDTO implements Serializable {

	@NotNull
	@Positive
	@JsonProperty("id")
	private Long id;

	@NotNull
	@Positive
	@JsonProperty("document_type")
	private Long documentType;

	@NotNull
	@JsonProperty("document_number")
	@Pattern(regexp = "^([0-9]{11}|[0-9]{14})$")
	private String documentNumber;

}
