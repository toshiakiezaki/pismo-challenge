package io.pismo.challenge.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import io.pismo.challenge.bean.AccountResponseDTO;
import io.pismo.challenge.configuration.DatabaseEnumType;
import io.pismo.challenge.domain.DocumentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@TypeDef(name = "document_type", typeClass = DatabaseEnumType.class)
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@Enumerated(STRING)
	@Type(type = "document_type")
	@Column(nullable = false, updatable = false, columnDefinition = "document_type")
	private DocumentType documentType;

	@Column(length = 14, nullable = false, updatable = false)
	private String documentNumber;

	@Builder.Default
	@Column(precision = 19, scale = 2, nullable = false)
	private BigDecimal availableCredit = BigDecimal.ZERO;

	public AccountResponseDTO toResponse() {
		return AccountResponseDTO.builder().id(id).documentType(documentType.code()).documentNumber(documentNumber).availableCredit(availableCredit).build();
	}

}
