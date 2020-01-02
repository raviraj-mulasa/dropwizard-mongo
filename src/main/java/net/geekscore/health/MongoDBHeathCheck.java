package net.geekscore.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoCursor;
import net.geekscore.AppConfiguration;
import net.geekscore.mongo.MongoDB;
import net.geekscore.utils.MongoDBUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBHeathCheck extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBHeathCheck.class);

    private final AppConfiguration configuration;

    public MongoDBHeathCheck(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected Result check() throws Exception {
        String mongoUrl = MongoDBUtils.url(configuration.getMongoDBSettings());
        Result result = Result.unhealthy(String.format("Cannot connect to %s", mongoUrl));
        try {
            ListDatabasesIterable<Document> databases = MongoDB.INSTANCE.client().listDatabases();
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
