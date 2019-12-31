package net.geekscore.core;

import com.mongodb.client.MongoCollection;
import org.slf4j.Logger;

public interface EntityService extends Loggable {

    <T extends BaseEntity> MongoCollection<T> collection();

    default <T extends BaseEntity> T save(T entityToSave) {
        Logger logger = logger();
        MongoCollection<T> collection = collection();
        entityToSave.setVersion(entityToSave.getVersion() + 1);
        collection.insertOne(entityToSave);
        return entityToSave;
    }

}
