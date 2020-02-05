package net.geekscore.resources;

import net.geekscore.core.DefaultResource;
import net.geekscore.core.EntityStore;
import net.geekscore.core.entities.Employer;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employer")
@Produces(MediaType.APPLICATION_JSON)
public class EmployerResource implements DefaultResource<Employer> {

    private final EntityStore<Employer> employerEntityStore;

    @Inject
    public EmployerResource(@NotNull EntityStore<Employer> employerEntityStore) {
        this.employerEntityStore = employerEntityStore;
    }

    @Override
    public EntityStore<Employer> store() {
        return this.employerEntityStore;
    }
}
