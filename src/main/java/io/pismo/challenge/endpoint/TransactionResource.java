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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.pismo.challenge.bean.AccountResponseDTO;
import io.pismo.challenge.bean.ErrorResponseDTO;
import io.pismo.challenge.bean.TransactionRequestDTO;
import io.pismo.challenge.bean.TransactionResponseDTO;
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
	// {@formatter:off}
	@Operation(summary = "Transaction creation", description = "Creates an transaction using the provided information.")
	@APIResponses(value = {
		@APIResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = TransactionResponseDTO.class))),
		@APIResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDTO[].class))),
		@APIResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponseDTO[].class)))
	})
	// {@formatter:on}
	public Response create(@Valid @RequestBody TransactionRequestDTO request) {
		return Response.status(Response.Status.CREATED).entity(transactionService.create(request)).build();
	}

}
