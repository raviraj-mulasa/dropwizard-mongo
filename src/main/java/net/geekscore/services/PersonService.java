package net.geekscore.services;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.Person;
import net.geekscore.mongo.MongoDB;
import net.geekscore.mongo.MongoEntityService;

import javax.inject.Inject;


public class PersonService implements MongoEntityService<Person> {

    @Inject
    public PersonService() {
    }

    @Override
    public MongoCollection<Person> collection() {
        return MongoDB.INSTANCE.database().getCollection("person", Person.class);
    }
}
