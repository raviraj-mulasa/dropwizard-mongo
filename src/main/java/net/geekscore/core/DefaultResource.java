package net.geekscore.core;

import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface DefaultResource<T extends BaseEntity> extends Loggable {

    Repository<T> repository();

    @GET
    @Path("/{id}")
    @Timed
    default T get(@NotEmpty @Valid @PathParam("id") String id) {
        return this.repository().findById(id);
    }

    @POST
    @Timed
    default T add(@NotNull @Valid T t) {
        return this.repository().save(t);
    }
}
