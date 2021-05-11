package io.pismo.challenge.mapper;

import static java.util.Objects.nonNull;

import java.lang.annotation.ElementType;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;
import org.hibernate.validator.internal.metadata.location.ConstraintLocation;

import io.pismo.challenge.bean.ErrorResponseDTO;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException e) {
		return Response.status(Status.BAD_REQUEST).entity(extractEntity(e)).build();
	}

	private List<ErrorResponseDTO> extractEntity(ConstraintViolationException exception) {
		var errors = new LinkedList<ErrorResponseDTO>();
		exception.getConstraintViolations().forEach(constraintViolation -> {
			var path = constraintViolation.getPropertyPath().toString();
			var errorBuilder = ErrorResponseDTO.builder().message(constraintViolation.getMessage()).value(constraintViolation.getInvalidValue());
			if (ConstraintDescriptorImpl.class.isAssignableFrom(constraintViolation.getConstraintDescriptor().getClass())) {
				var elementType = Optional.ofNullable(((ConstraintDescriptorImpl) constraintViolation.getConstraintDescriptor()).getConstraintLocationKind())
						.map(ConstraintLocation.ConstraintLocationKind::getElementType).orElse(null);
				if (nonNull(elementType)) {
					errorBuilder.kind(elementType.toString());
					if (elementType == ElementType.FIELD) {
						path = extractPath(path);
					}
				}
			}
			errorBuilder.path(path);
			errors.add(errorBuilder.build());
		});
		return errors;
	}

	private String extractPath(String path) {
		var pathBuilder = new StringBuilder();
		var parts = path.split("\\.");
		var index = parts.length > 2 ? 2 : 0;
		while (index < parts.length) {
			pathBuilder.append(parts[index]);
			index++;
		}
		return pathBuilder.toString();
	}

}
