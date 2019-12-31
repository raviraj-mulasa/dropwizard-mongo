package net.geekscore.service;


import net.geekscore.api.Greeting;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicLong;


public class GreetServiceImpl implements GreetService {

    private final AtomicLong counter;

    private final PersonService personService;

//    @Inject
//    public GreetServiceImpl() {
//        this.counter = new AtomicLong();
//    }

    @Inject
    public GreetServiceImpl(@NotNull PersonService personService) {
        this.personService = personService;
        this.counter = new AtomicLong();
    }

    @Override
    public Greeting greet(String name) {
        this.personService.save(null);
        return new Greeting(counter.incrementAndGet(), String.format("Hello %s",name));

    }
}
