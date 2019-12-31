package net.geekscore.resources;

import com.codahale.metrics.annotation.Timed;
import net.geekscore.core.Address;
import net.geekscore.core.EntityService;
import net.geekscore.core.Person;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final EntityService entityService;

    @Inject
    public PersonResource(
            @NotNull @Named("person") EntityService entityService
    ) {
        this.entityService = entityService;
    }

    @GET
    @Timed
    public Person add() {
        Person ada = new Person("Ada Byron", 20, new Address("St James Square", "London", "W1"));
        return this.entityService.save(ada);
    }
}
