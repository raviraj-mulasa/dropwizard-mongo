package net.geekscore.core.repositories;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.entities.Person;
import net.geekscore.mongo.MongoDB;
import net.geekscore.mongo.MongoRepository;

public class PersonRepository implements MongoRepository<Person> {

    @Override
    public MongoCollection<Person> collection() {
        return MongoDB.INSTANCE.database().getCollection("person", Person.class);
    }
}
