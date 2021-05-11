package io.pismo.challenge.mapper;

import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.pismo.challenge.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(BusinessException e) {
		return Response.status(e.getStatus()).entity(List.of(e.toResponse())).build();
	}

}
