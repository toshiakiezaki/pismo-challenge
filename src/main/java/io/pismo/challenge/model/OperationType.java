package io.pismo.challenge.model;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.pismo.challenge.domain.OperationEntry;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

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
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class OperationType extends PanacheEntityBase implements Serializable {

	@Id
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(length = 120, nullable = false)
	private String description;

	@Enumerated(STRING)
	@Column(nullable = false, updatable = false)
	private OperationEntry entry;

}
