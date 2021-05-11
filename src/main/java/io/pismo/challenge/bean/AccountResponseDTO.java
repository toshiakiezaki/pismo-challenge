package io.pismo.challenge.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
	@Size(min = 11, max = 14)
	private String documentNumber;

}
