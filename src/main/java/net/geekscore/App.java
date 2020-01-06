package net.geekscore;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.geekscore.core.EntityService;
import net.geekscore.core.Person;
import net.geekscore.health.MongoDBHeathCheck;
import net.geekscore.mongo.MongoDBSettings;
import net.geekscore.mongo.MongoEntityService;
import net.geekscore.mongo.MongoJacksonModule;
import net.geekscore.resources.GreetResource;
import net.geekscore.resources.PersonResource;
import net.geekscore.services.GreetService;
import net.geekscore.services.GreetServiceImpl;
import net.geekscore.services.PersonService;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class App extends Application<AppConfiguration> {


    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard-MongoDB";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/fonts", "/fonts", null, "fonts"));
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {

        ObjectMapper objectMapper = environment.getObjectMapper();
        objectMapper.registerModule(new MongoJacksonModule());


        JerseyEnvironment jersey = environment.jersey();
        final MongoDBSettings mongoDBSettings = configuration.getMongoDBSettings();
        mongoDBSettings.build(environment);


        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {

                bind(PersonService.class)
//                        .named("person")
                        .to(new TypeLiteral<MongoEntityService<Person>>() {})
                        .to(new TypeLiteral<EntityService<Person>>() {})
                        .in(Singleton.class);

                bind(GreetServiceImpl.class).to(GreetService.class).in(Singleton.class);


            }
        });

        jersey.packages("net.geekscore.resources");
//        jersey.register(GreetResource.class);
//        jersey.register(PersonResource.class);

        environment.healthChecks().register("mongoDB", new MongoDBHeathCheck(configuration));

    }
}
