package net.geekscore.core;

import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

public interface DefaultResource<T extends BaseEntity> extends Loggable {

    EntityStore<T> store();

    @PUT
    @Timed
    default T create(@NotNull @Valid T t) {
        return this.store().upsert(t);
    }

    @GET
    @Path("/{id}")
    @Timed
    default T retrieve(@NotEmpty @Valid @PathParam("id") String id) {
        return this.store().findById(id);
    }

    @POST
    @Timed
    default T update(@NotNull @Valid T t) {
        return this.store().upsert(t);
    }

    @DELETE
    @Path("/{id}")
    @Timed
    default T delete(@NotEmpty @Valid @PathParam("id") String id) {
        return this.store().deleteById(id);
    }

    @GET
    @Path("/test")
    default void test() {
        System.out.println("Default TEST in DEfaultResource");;
    }
}
