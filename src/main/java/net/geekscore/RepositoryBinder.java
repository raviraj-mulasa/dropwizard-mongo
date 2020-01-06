package net.geekscore;

import net.geekscore.core.entities.Employer;
import net.geekscore.core.Repository;
import net.geekscore.core.entities.Person;
import net.geekscore.core.repositories.EmployerRepository;
import net.geekscore.core.repositories.PersonRepository;
import net.geekscore.mongo.MongoRepository;
import net.geekscore.services.GreetService;
import net.geekscore.services.GreetServiceImpl;
import net.geekscore.services.PersonService;
import net.geekscore.services.PersonServiceImpl;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class RepositoryBinder extends AbstractBinder {
    @Override
    protected void configure() {

        bind(PersonRepository.class)
                .to(new TypeLiteral<MongoRepository<Person>>() {})
                .to(new TypeLiteral<Repository<Person>>() {})
                .in(Singleton.class);

        bind(EmployerRepository.class)
                .to(new TypeLiteral<MongoRepository<Employer>>() {})
                .to(new TypeLiteral<Repository<Employer>>() {})
                .in(Singleton.class);

        bind(GreetServiceImpl.class)
                .to(GreetService.class)
                .in(Singleton.class);

        bind(PersonServiceImpl.class)
                .to(PersonService.class)
                .in(Singleton.class);

    }
}
