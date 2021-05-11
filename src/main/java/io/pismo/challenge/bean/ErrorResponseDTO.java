package io.pismo.challenge.bean;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.io.Serializable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@JsonInclude(NON_EMPTY)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Schema(description = "Object that represents a error response.")
public class ErrorResponseDTO implements Serializable {

	@JsonProperty(value = "path")
	@Schema(description = "Property path of the related error.")
	private String path;

	@JsonProperty(value = "message")
	@Schema(description = "Error message description.")
	private String message;

	@JsonProperty(value = "value")
	@Schema(description = "Current value of the property.")
	private Object value;

	@JsonProperty(value = "kind")
	@Schema(description = "Property kind of the related error.")
	private String kind;

}
