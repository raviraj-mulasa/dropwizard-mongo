package net.geekscore;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import net.geekscore.mongo.MongoDBSettings;

public class AppConfiguration extends Configuration {

    @JsonProperty("mongo")
    private MongoDBSettings mongoClientSettings;

    @JsonProperty("mongo")
    public MongoDBSettings getMongoClientSettings() {
        return this.mongoClientSettings;
    }
}
