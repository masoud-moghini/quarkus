package quarkus.bank;


import configs.BankSupportConfig;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.Optional;

@Path("/bank")
public class BankResource {
    @ConfigProperty(name="bank.name")
    Optional<String> name;

    @ConfigProperty(name="username")
    Optional<String> username;

    @ConfigProperty(name="password")
    Optional<String> password;

    @Inject
    BankSupportConfig bankSupportConfig;

    @Inject
    BankResourceConfig bankResourceConfig;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return name.orElse("not found");
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/support")
    public Map sendSupportInfo(){
        return Map.of("email",bankSupportConfig.email,"phone", bankSupportConfig.getPhone());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/secret")
    public Map sendSecrets(){
        return Map.of("username",username,"password", password);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/bank-support")
    public Map sendBankSupportInfo(){
        return Map.of(
                "email",bankResourceConfig.email(),"phone", bankResourceConfig.phone(),
                "business_email",bankResourceConfig.business().email(),"business_phone", bankResourceConfig.business().phone()
        );
    }
}