package net.geekscore.core;

import net.geekscore.core.entities.Employer;
import net.geekscore.core.entities.Person;
import net.geekscore.core.stores.EmployerEntityStore;
import net.geekscore.core.stores.PersonEntityStore;
import net.geekscore.mongo.Collection;
import net.geekscore.mongo.CollectionInjectResolver;
import net.geekscore.mongo.MongoEntityStore;
import net.geekscore.services.GreetService;
import net.geekscore.services.GreetServiceImpl;
import net.geekscore.services.PersonService;
import net.geekscore.services.PersonServiceImpl;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class EntityStoreBinder extends AbstractBinder {
    @Override
    protected void configure() {

        bind(PersonEntityStore.class)
                .to(new TypeLiteral<MongoEntityStore<Person>>() {})
                .to(new TypeLiteral<EntityStore<Person>>() {})
                .in(Singleton.class);

        bind(EmployerEntityStore.class)
                .to(new TypeLiteral<MongoEntityStore<Employer>>() {})
                .to(new TypeLiteral<EntityStore<Employer>>() {})
                .in(Singleton.class);

        bind(GreetServiceImpl.class)
                .to(GreetService.class)
                .in(Singleton.class);

        bind(PersonServiceImpl.class)
                .to(PersonService.class)
                .in(Singleton.class);

        bind(CollectionInjectResolver.class)
                .to(new TypeLiteral<InjectionResolver<Collection>>(){})
                .in(Singleton.class);

    }
}
