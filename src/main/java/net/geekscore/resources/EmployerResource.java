package net.geekscore.resources;

import net.geekscore.core.DefaultResource;
import net.geekscore.core.Repository;
import net.geekscore.core.entities.Employer;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employer")
@Produces(MediaType.APPLICATION_JSON)
public class EmployerResource extends DefaultResource<Employer> {

    @Inject
    public EmployerResource(@NotNull Repository<Employer> employerRepository) {
        super(employerRepository);
    }

}
