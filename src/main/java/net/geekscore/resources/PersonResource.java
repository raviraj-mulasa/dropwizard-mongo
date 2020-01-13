package net.geekscore.resources;

import net.geekscore.core.DefaultResource;
import net.geekscore.core.EntityStore;
import net.geekscore.core.entities.Person;
import net.geekscore.services.PersonService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource extends DefaultResource<Person> {

    private final PersonService personService;

    @Inject
    public PersonResource(
            @NotNull EntityStore<Person> personEntityStore,
            @NotNull PersonService personService
    ) {
        super(personEntityStore);
        this.personService = personService;
    }

    @GET
    @Path("/test")
    public void test() {
        this.personService.test();
    }
}
