package io.pismo.challenge.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.pismo.challenge.bean.TransactionRequestDTO;
import io.pismo.challenge.service.TransactionService;

@ApplicationScoped
@Path("/api/transactions")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "Transaction management")
public class TransactionResource {

	@Inject
	TransactionService transactionService;

	@POST
	public Response create(@Valid @RequestBody TransactionRequestDTO request) {
		return Response.status(Response.Status.CREATED).entity(transactionService.create(request)).build();
	}

}
