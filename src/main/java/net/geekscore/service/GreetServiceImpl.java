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

    @Inject
    public GreetServiceImpl() {
        this.counter = new AtomicLong();
    }


    @Override
    public Greeting greet(String name) {
        return new Greeting(counter.incrementAndGet(), String.format("Hello %s",name));

    }
}
