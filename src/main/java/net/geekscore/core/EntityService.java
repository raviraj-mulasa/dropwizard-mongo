package net.geekscore.core;

import com.mongodb.client.MongoCollection;

public interface EntityService {

    <T extends BaseEntity> MongoCollection<T> collection();

    default <T extends BaseEntity> T save(T entityToSave) {
        MongoCollection<T> collection = collection();
        collection.insertOne(entityToSave);
        return entityToSave;
    }

}
