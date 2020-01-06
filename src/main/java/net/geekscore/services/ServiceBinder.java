package net.geekscore.services;

import net.geekscore.core.Employer;
import net.geekscore.core.Repository;
import net.geekscore.core.Person;
import net.geekscore.mongo.MongoRepository;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class ServiceBinder extends AbstractBinder {
    @Override
    protected void configure() {

        bind(PersonServiceImpl.class)
                .to(new TypeLiteral<MongoRepository<Person>>() {})
                .to(new TypeLiteral<Repository<Person>>() {})
                .to(PersonService.class)
                .in(Singleton.class);

        bind(EmployerService.class)
                .to(new TypeLiteral<MongoRepository<Employer>>() {})
                .to(new TypeLiteral<Repository<Employer>>() {})
                .in(Singleton.class);

        bind(GreetServiceImpl.class)
                .to(GreetService.class)
                .in(Singleton.class);

    }
}
