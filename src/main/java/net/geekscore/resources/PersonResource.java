package net.geekscore.resources;

import com.codahale.metrics.annotation.Timed;
import net.geekscore.core.EntityService;
import net.geekscore.core.Person;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final EntityService<Person> entityService;

    @Inject
    public PersonResource(@NotNull EntityService<Person> entityService) {
        this.entityService = entityService;
    }

    @GET
    @Path("/{id}")
    @Timed
    public Person get(@NotEmpty @Valid @PathParam("id") String id) {
        return this.entityService.findById(id);
    }

    @POST
    @Timed
    public Person add(@NotNull @Valid Person person) {
        return this.entityService.save(person);
    }
}
