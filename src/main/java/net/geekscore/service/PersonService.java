package net.geekscore.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.geekscore.core.EntityService;
import net.geekscore.core.Person;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class PersonService implements EntityService {

    private final Logger logger = logger();

    private final MongoCollection<Person> collection;

    @Inject
    public PersonService(@NotNull @Valid MongoDatabase database) {
        this.collection = database.getCollection("person", Person.class);
    }

    @Override
    public MongoCollection<Person> collection() {
        return this.collection;
    }
}
