package net.geekscore.core.stores;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.entities.Employer;
import net.geekscore.mongo.Collection;
import net.geekscore.mongo.MongoEntityStore;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

public class EmployerEntityStore extends MongoEntityStore<Employer> {
    @Inject
    public EmployerEntityStore(@NotNull @Collection MongoCollection<Employer> employerCollection) {
        super(employerCollection);
    }

}
