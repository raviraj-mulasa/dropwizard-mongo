package net.geekscore.service;


import net.geekscore.api.Greeting;
import net.geekscore.core.Address;
import net.geekscore.core.EntityService;
import net.geekscore.core.Person;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicLong;


public class GreetServiceImpl implements GreetService {

    private final AtomicLong counter;

    private final EntityService entityService;

    @Inject
    public GreetServiceImpl(@NotNull @Named("person") EntityService entityService) {
        this.entityService = entityService;
        this.counter = new AtomicLong();
    }


    @Override
    public Greeting greet(String name) {
        Person ada = new Person("Ada Byron", 20, new Address("St James Square", "London", "W1"));
        this.entityService.save(ada);
        return new Greeting(counter.incrementAndGet(), String.format("Hello %s",name));

    }
}
