package net.geekscore.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import net.geekscore.AppConfiguration;
import net.geekscore.mongo.MongoDBSettings;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBHeathCheck extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBHeathCheck.class);

    private final AppConfiguration configuration;

    private final MongoClient mongoClient;

    public MongoDBHeathCheck(
            AppConfiguration configuration
            , MongoClient mongoClient
    ) {
        this.configuration = configuration;
        this.mongoClient = mongoClient;
    }

    @Override
    protected Result check() throws Exception {

        MongoDBSettings mongoDBSettings = configuration.getMongoDBSettings();
        String mongoUrl = String.format(
                "mongodb://%s:XXXXX@%s:%d/%s"
                , mongoDBSettings.getUsername()
                , mongoDBSettings.getHostname()
                , mongoDBSettings.getPort()
                , mongoDBSettings.getDatabase()
        );

        Result result = Result.unhealthy(String.format("Cannot connect to %s", mongoUrl));
        try {
            ListDatabasesIterable<Document> databases = mongoClient.listDatabases();
            MongoCursor<Document> mongoCursor = databases.cursor();
            if (mongoCursor.tryNext() != null) {
                result =  Result.healthy();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
