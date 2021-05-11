package io.pismo.challenge.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
	private Long id;

	@NotNull
	@Positive
	private Long documentType;

	@NotNull
	@Pattern(regexp = "^([0-9]{11}|[0-9]{14})$")
	private String documentNumber;

}
