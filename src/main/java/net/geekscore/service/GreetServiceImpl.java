package net.geekscore.service;


import net.geekscore.api.Greeting;

import javax.inject.Inject;
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
