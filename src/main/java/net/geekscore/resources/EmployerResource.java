package net.geekscore.resources;

import net.geekscore.core.Employer;
import net.geekscore.core.Repository;
import net.geekscore.core.DefaultResource;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/employer")
@Produces(MediaType.APPLICATION_JSON)
public class EmployerResource implements DefaultResource<Employer> {

    private final Repository<Employer> employerRepository;

    @Inject
    public EmployerResource(@NotNull Repository<Employer> employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public Repository<Employer> repository() {
        return this.employerRepository;
    }
}
