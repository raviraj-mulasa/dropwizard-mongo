package net.geekscore;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.geekscore.core.BaseEntity;
import net.geekscore.core.Person;
import net.geekscore.health.MongoDBHeathCheck;
import net.geekscore.mongo.MongoClientManager;
import net.geekscore.mongo.MongoDBSettings;
import net.geekscore.resources.GreetResource;
import net.geekscore.service.GreetService;
import net.geekscore.service.GreetServiceImpl;
import net.geekscore.service.PersonService;
import net.geekscore.service.PersonServiceImpl;
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


        JerseyEnvironment jersey = environment.jersey();

        final MongoDBSettings dbSettings = configuration.getMongoDBSettings();
        final MongoClient mongoClient = dbSettings.build(environment);
        MongoDatabase database = mongoClient.getDatabase(dbSettings.getDatabase());

        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {
//                Type type = new TypeLiteral<EntityService<Person>>() {}.getType();

//                ActiveDescriptor<?> ad = BuilderHelper.activeLink(PersonServiceImpl.class)
//                        .to(type.getClass())
//                        .in(Singleton.class)
//                        .build();
//                bind(ad);

                bind(PersonServiceImpl.class).to(PersonService.class).in(Singleton.class);
                bind(GreetServiceImpl.class).to(GreetService.class).in(Singleton.class);
                bind(database).to(MongoDatabase.class);

            }
        });

        jersey.register(GreetResource.class);

        environment.healthChecks().register("mongoDB", new MongoDBHeathCheck(configuration, mongoClient));

    }
}
