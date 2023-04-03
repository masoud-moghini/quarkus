package io.quarkus.services;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Path("/accounts")
@RegisterRestClient(configKey = "account-service")
public interface AccountService {

    @POST
    @Path("/{accountNumber}/transaction")
    public Map<String, List<String>> transact(@PathParam("accountNumber") Long accountNumber, BigDecimal amount);
}
