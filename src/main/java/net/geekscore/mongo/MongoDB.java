package net.geekscore.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.SslSettings;
import net.geekscore.mongo.utils.MongoDBUtils;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

public enum MongoDB {

    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(MongoDB.class);

    private  MongoClient client;

    private  MongoDatabase database;

    public void init(@NotNull @Valid MongoDBSettings mongoDBSettings) {

        if(null == this.database ) {
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry()
                    , CodecRegistries.fromProviders(
                            PojoCodecProvider.builder().automatic(true).build()
                    )
            );

            MongoCredential credential = MongoCredential.createCredential(
                    mongoDBSettings.getUsername()
                    , mongoDBSettings.getAuthDatabase()
                    , mongoDBSettings.getPassword().toCharArray()
            );


            MongoClientSettings mongoClientSettings = MongoClientSettings
                    .builder()
                    .applicationName("Dropwizard-MongoDB")
                    .codecRegistry(pojoCodecRegistry)
                    .credential(credential)
                    .applyToClusterSettings(builder ->
                            builder.hosts(
                                    Arrays.asList(
                                            new ServerAddress(mongoDBSettings.getHostname(), mongoDBSettings.getPort())
                                    )
                            )
                    )
                    .applyToSslSettings(builder ->
                            builder.applySettings(SslSettings.builder().enabled(false).build())) // TODO
                    .build();

            logger.debug("Connecting :{}", MongoDBUtils.url(mongoDBSettings));
            this.client = MongoClients.create(mongoClientSettings);
            this.database = this.client.getDatabase(mongoDBSettings.getDatabase());
            logger.debug("Connected :{}", MongoDBUtils.url(mongoDBSettings));

        }
    }


    public void tearDown(){
        if(null != this.client) {
            logger.debug("Closing mongo client");
            this.client.close();
            logger.debug("Closed mongo client");
        }
    }

    public MongoClient client() {
        return this.client;
    }

    public MongoDatabase database() {
        return this.database;
    }
}
