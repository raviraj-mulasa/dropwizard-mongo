package net.geekscore.core.stores;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.entities.Person;
import net.geekscore.mongo.Collection;
import net.geekscore.mongo.MongoEntityStore;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;


public class PersonEntityStore extends MongoEntityStore<Person> {
    @Inject
    public PersonEntityStore(@NotNull @Collection MongoCollection<Person> personCollection) {
        super(personCollection);
    }

    public void x(){}
}
