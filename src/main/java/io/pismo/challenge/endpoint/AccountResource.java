package io.pismo.challenge.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.pismo.challenge.bean.AccountRequestDTO;
import io.pismo.challenge.bean.AccountResponseDTO;
import io.pismo.challenge.bean.ErrorResponseDTO;
import io.pismo.challenge.service.AccountService;

@ApplicationScoped
@Path("/api/accounts")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "Account management")
public class AccountResource {

	@Inject
	AccountService accountService;

	@POST
	// {@formatter:off}
	@Operation(summary = "Account creation", description = "Creates an account using the provided information.")
	@APIResponses(value = {
		@APIResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
		@APIResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDTO[].class)))
	})
	// {@formatter:on}
	public Response create(@Valid @RequestBody AccountRequestDTO request) {
		return Response.status(Status.CREATED).entity(accountService.create(request)).build();
	}

	@GET
	@Path("/{id}")
	// {@formatter:off}
	@Operation(summary = "Account search by ID", description = "Searches for an account using its identifier.")
	@Parameters(value = {
		@Parameter(name = "id",  description = "A positive number that identifies an account.", required = true, example = "1")
	})
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
		@APIResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDTO[].class))),
		@APIResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponseDTO[].class)))
	})
	// {@formatter:on}
	public Response findById(@Positive @PathParam("id") Long id) {
		return Response.status(Status.OK).entity(accountService.findById(id)).build();
	}

}
