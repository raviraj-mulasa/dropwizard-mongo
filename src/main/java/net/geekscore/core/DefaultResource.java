package net.geekscore.core;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public abstract class DefaultResource<T extends BaseEntity> implements Loggable {

    protected final Logger logger = this.logger();

    protected final EntityStore<T> repository;

    public DefaultResource(EntityStore<T> repository) {
        this.repository = repository;
    }

    @GET
    @Path("/{id}")
    @Timed
    public T get(@NotEmpty @Valid @PathParam("id") String id) {
        return this.repository.findById(id);
    }

    @POST
    @Timed
    public T add(@NotNull @Valid T t) {
        return this.repository.save(t);
    }
}
