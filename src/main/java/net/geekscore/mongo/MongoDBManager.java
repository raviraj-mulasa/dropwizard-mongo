package net.geekscore.mongo;

import io.dropwizard.lifecycle.Managed;

public class MongoDBManager implements Managed {

    private final MongoDBSettings mongoDBSettings;

    public MongoDBManager(MongoDBSettings mongoDBSettings) {
        this.mongoDBSettings = mongoDBSettings;

    }

    @Override
    public void start() throws Exception {
        MongoDB.INSTANCE.init(mongoDBSettings);
    }

    @Override
    public void stop() throws Exception {
        MongoDB.INSTANCE.tearDown();
    }

}
