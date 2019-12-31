package net.geekscore.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.lifecycle.Managed;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoClientManager implements Managed {

    private static final Logger logger = LoggerFactory.getLogger(MongoClientManager.class);

    private final MongoDBSettings mongoDBSettings;

    private MongoClient mongoClient;

    public MongoClientManager(MongoDBSettings mongoDBSettings) {
        this.mongoDBSettings = mongoDBSettings;
        this.init();
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        if(null != mongoClient) {
            logger.debug("Closing mongo client");
            this.mongoClient.close();
            logger.debug("Closed mongo client");
        }
    }


    public MongoClient getMongoClient() {
        return this.mongoClient;
    }

    private void init() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry()
                , CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true).build()
                )
        );

        MongoCredential credential = MongoCredential.createCredential(
                this.mongoDBSettings.getUsername()
                , this.mongoDBSettings.getAuthDatabase()
                , this.mongoDBSettings.getPassword().toCharArray()
        );


        MongoClientOptions options = MongoClientOptions
                .builder()
                .applicationName("Dropwizard-MongoDB")
                .codecRegistry(pojoCodecRegistry)
                .sslEnabled(false)
                .build();

        this.mongoClient = new MongoClient(
                new ServerAddress(
                        this.mongoDBSettings.getHostname()
                        , this.mongoDBSettings.getPort()
                ),
                credential,
                options
        );
    }
}
