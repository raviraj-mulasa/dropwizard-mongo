package net.geekscore.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.geekscore.core.Address;
import net.geekscore.core.Person;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class PersonServiceImpl implements PersonService {

    private final MongoCollection<Person> collection;

    private final MongoDatabase database;

    @Inject
    public PersonServiceImpl(@NotNull @Valid MongoDatabase database) {
        this.database = database;
        this.collection = this.database.getCollection("person", Person.class);
    }

    @Override
    public  Person save(Person person) {
        Person ada = new Person("Ada Byron", 20, new Address("St James Square", "London", "W1"));
        collection.insertOne(ada);
        return  ada;
    }



}
