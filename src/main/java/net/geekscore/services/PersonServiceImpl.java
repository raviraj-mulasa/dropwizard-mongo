package net.geekscore.services;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.Person;
import net.geekscore.mongo.MongoDB;
import net.geekscore.mongo.MongoRepository;

import javax.inject.Inject;


public class PersonServiceImpl implements MongoRepository<Person>, PersonService {

    @Inject
    public PersonServiceImpl() {
    }

    @Override
    public MongoCollection<Person> collection() {
        return MongoDB.INSTANCE.database().getCollection("person", Person.class);
    }

    @Override
    public void test() {
        System.out.println("Reaching PERSON SERVICE IMPL ");
    }
}
