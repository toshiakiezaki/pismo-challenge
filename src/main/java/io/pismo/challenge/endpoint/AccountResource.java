package io.pismo.challenge.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.pismo.challenge.bean.AccountRequestDTO;
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
	public Response create(@Valid @RequestBody AccountRequestDTO request) {
		return Response.status(Status.CREATED).entity(accountService.create(request)).build();
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		return Response.status(Status.OK).entity(accountService.findById(id)).build();
	}

}
