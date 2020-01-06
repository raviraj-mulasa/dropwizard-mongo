package net.geekscore.mongo.utils;

import net.geekscore.mongo.MongoDBSettings;

public final class MongoDBUtils {

    private MongoDBUtils() { }

    public static final String url(MongoDBSettings mongoDBSettings) {
        String mongoUrl = String.format(
                "mongodb://%s:XXXXX@%s:%d/%s"
                , mongoDBSettings.getUsername()
                , mongoDBSettings.getHostname()
                , mongoDBSettings.getPort()
                , mongoDBSettings.getDatabase()
        );
        return mongoUrl;
    }
}
