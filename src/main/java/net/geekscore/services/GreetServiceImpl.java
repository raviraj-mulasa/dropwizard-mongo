package net.geekscore.services;


import net.geekscore.api.Greeting;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    @PostConstruct
    public void init() {
        System.out.println("postConstruct.....");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy.....");
    }
}
