package net.geekscore.core.stores;

import com.mongodb.client.MongoCollection;
import net.geekscore.mongo.Collection;
import net.geekscore.core.entities.Employer;
import net.geekscore.mongo.MongoEntityStore;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

public class EmployerEntityStore implements MongoEntityStore<Employer> {

    private final MongoCollection<Employer> employerCollection;

    @Inject
    public EmployerEntityStore(@NotNull @Collection MongoCollection<Employer> employerCollection) {
        this.employerCollection = employerCollection;
    }

    @Override
    public MongoCollection<Employer> collection() {
        return this.employerCollection;
    }
}
