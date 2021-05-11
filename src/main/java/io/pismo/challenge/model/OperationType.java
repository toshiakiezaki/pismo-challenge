package io.pismo.challenge.model;

import static io.pismo.challenge.domain.OperationEntry.CREDIT;
import static io.pismo.challenge.domain.OperationEntry.DEBIT;
import static java.util.Objects.isNull;
import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import io.pismo.challenge.configuration.DatabaseEnumType;
import io.pismo.challenge.domain.OperationEntry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@Immutable
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@TypeDef(name = "operation_entry", typeClass = DatabaseEnumType.class)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class OperationType implements Serializable {

	@Id
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(length = 120, nullable = false)
	private String description;

	@Enumerated(STRING)
	@Type(type = "operation_entry")
	@Column(nullable = false, updatable = false, columnDefinition = "operation_entry")
	private OperationEntry entry;

	public boolean isCredit() {
		if (isNull(entry)) {
			return false;
		}
		return entry == CREDIT;
	}

}
