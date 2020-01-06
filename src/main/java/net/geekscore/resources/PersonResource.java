package net.geekscore.resources;

import net.geekscore.core.DefaultResource;
import net.geekscore.core.Repository;
import net.geekscore.core.Person;
import net.geekscore.services.PersonService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource implements DefaultResource<Person> {

    private final Repository<Person> personRepository;

    private final PersonService personService;

    @Inject
    public PersonResource(
            @NotNull Repository<Person> personRepository,
            @NotNull PersonService personService
    ) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @Override
    public Repository<Person> repository() {
        return this.personRepository;
    }

    @GET
    @Path("/test")
    public void test() {
        this.personService.test();
    }
}
