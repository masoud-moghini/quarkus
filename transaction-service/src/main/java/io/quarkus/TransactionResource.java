package io.quarkus;

import io.quarkus.services.AccountService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/transactions")
public class TransactionResource {
    @Inject
    @RestClient
    AccountService accountService;


    @POST
    @Path("/{accountNum}")
    public Response newTransaction(@PathParam("accountNum") Long accountNumber, BigDecimal amount){
        accountService.transact(accountNumber,amount);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
}