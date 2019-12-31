package net.geekscore.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoClientManager implements Managed {

    private static final Logger logger = LoggerFactory.getLogger(MongoClientManager.class);

    private final MongoClientSettings mongoClientSettings;

    private MongoClient mongoClient;

    public MongoClientManager(MongoClientSettings mongoClientSettings) {
        this.mongoClientSettings = mongoClientSettings;
    }

    @Override
    public void start() throws Exception {
        MongoCredential credential = MongoCredential.createCredential(
                this.mongoClientSettings.getHostname()
                , this.mongoClientSettings.getDatabase()
                , this.mongoClientSettings.getPassword().toCharArray()
        );
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build(); // TODO
        this.mongoClient = new MongoClient(
                new ServerAddress(mongoClientSettings.getHostname(), 27017)
                , credential
                , options
        );
    }

    @Override
    public void stop() throws Exception {
        if(null != mongoClient) {
            logger.debug("Closing mongo client");
            this.mongoClient.close();
            logger.debug("Closed mongo client");
        }
    }
}
