package net.geekscore.services;

import net.geekscore.core.entities.Employer;
import net.geekscore.core.entities.Person;
import net.geekscore.core.EntityStore;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;


public class PersonServiceImpl implements PersonService {

    private final EntityStore<Person> personEntityStore;

    private final EntityStore<Employer> employerEntityStore;

    @Inject
    public PersonServiceImpl(
            @NotNull EntityStore<Person> personEntityStore
            ,@NotNull EntityStore<Employer> employerEntityStore
    ) {
        this.personEntityStore = personEntityStore;
        this.employerEntityStore = employerEntityStore;
    }

    @Override
    public void test() {
        System.out.println("Reaching PERSON SERVICE IMPL ");
    }
}
