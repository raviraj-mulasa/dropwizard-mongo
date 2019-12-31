package net.geekscore;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import net.geekscore.mongo.MongoDBSettings;

public class AppConfiguration extends Configuration {

    @JsonProperty("mongo")
    private MongoDBSettings mongoDBSettings;

    @JsonProperty("mongo")
    public MongoDBSettings getMongoDBSettings() {
        return this.mongoDBSettings;
    }
}
