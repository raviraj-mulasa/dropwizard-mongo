package net.geekscore.core.stores;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.entities.Person;
import net.geekscore.mongo.Collection;
import net.geekscore.mongo.MongoEntityStore;

import javax.validation.constraints.NotNull;

public class PersonEntityStore implements MongoEntityStore<Person> {

    private final MongoCollection<Person> personCollection;

    public PersonEntityStore(@NotNull @Collection MongoCollection<Person> personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public MongoCollection<Person> collection() {
        return this.personCollection;
    }
}
