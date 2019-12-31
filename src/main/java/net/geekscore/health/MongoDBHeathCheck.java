package net.geekscore.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoCursor;
import net.geekscore.mongo.MongoClientManager;
import net.geekscore.mongo.MongoDBSettings;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBHeathCheck extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBHeathCheck.class);

    private final MongoClientManager mongoClientManager;

    public MongoDBHeathCheck(MongoClientManager MongoClientManager) {
        this.mongoClientManager = MongoClientManager;
    }

    @Override
    protected Result check() throws Exception {

        MongoDBSettings mongoDBSettings = this.mongoClientManager.getMongoDBSettings();
        String mongoUrl = String.format(
                "mongodb://%s:XXXXX@%s:%d/%s"
                , mongoDBSettings.getUsername()
                , mongoDBSettings.getHostname()
                , mongoDBSettings.getPort()
                , mongoDBSettings.getDatabase()
        );

        Result result = Result.unhealthy(String.format("Cannot connect to %s", mongoUrl));
        try {
            MongoClient mongoClient = this.mongoClientManager.getMongoClient();
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
