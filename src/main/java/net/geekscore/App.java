package net.geekscore;

import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.geekscore.health.MongoDBHeathCheck;
import net.geekscore.mongo.MongoClientManager;
import net.geekscore.resources.GreetResource;
import net.geekscore.service.GreetService;
import net.geekscore.service.GreetServiceImpl;
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
        // nothing to do yet
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {

        JerseyEnvironment jersey = environment.jersey();

        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(GreetServiceImpl.class).to(GreetService.class).in(Singleton.class);
            }
        });

        jersey.register(GreetResource.class);

        final MongoClientManager mongoClientManager = new MongoClientManager(configuration.getMongoClientSettings());
        environment.lifecycle().manage(mongoClientManager);

        environment.healthChecks().register("mongoDB", new MongoDBHeathCheck(mongoClientManager));

    }
}
