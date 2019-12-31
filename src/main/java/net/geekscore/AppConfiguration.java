package net.geekscore;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import net.geekscore.mongo.MongoClientSettings;

public class AppConfiguration extends Configuration {

    @JsonProperty("mongo")
    private MongoClientSettings mongoClientSettings;

    @JsonProperty("mongo")
    public MongoClientSettings getMongoClientSettings() {
        return this.mongoClientSettings;
    }
}
