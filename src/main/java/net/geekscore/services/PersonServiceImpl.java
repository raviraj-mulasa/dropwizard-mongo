package net.geekscore.services;

import net.geekscore.core.domain.Person;
import net.geekscore.core.Repository;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;


public class PersonServiceImpl implements PersonService {

    private final Repository<Person> personRepository;

    @Inject
    public PersonServiceImpl(@NotNull Repository<Person> personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void test() {
        System.out.println("Reaching PERSON SERVICE IMPL ");
    }
}
