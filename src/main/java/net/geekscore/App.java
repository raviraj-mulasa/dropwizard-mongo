package net.geekscore;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.geekscore.health.MongoDBHeathCheck;
import net.geekscore.mongo.MongoDBSettings;
import net.geekscore.mongo.MongoJacksonModule;

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

        jersey.register(new RepositoryBinder()); // Register Services
        jersey.packages("net.geekscore.resources"); // Register resources

        environment.healthChecks().register("mongoDB", new MongoDBHeathCheck(configuration));

    }
}
