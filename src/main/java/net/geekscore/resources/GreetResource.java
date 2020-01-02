package net.geekscore.resources;

import com.codahale.metrics.annotation.Timed;
import net.geekscore.api.Greeting;
import net.geekscore.services.GreetService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/greet")
@Produces(MediaType.APPLICATION_JSON)
public class GreetResource {

    private final GreetService greetService;

    @Inject
    public GreetResource(@NotNull GreetService greetService) {
        this.greetService = greetService;
    }

    @GET
    @Timed
    public Greeting greet(@QueryParam("name") Optional<String> name) {
        return this.greetService.greet(name.orElse("Anonymous"));
    }
}
