package net.geekscore.core;

import com.mongodb.client.MongoCollection;

public interface EntityService<T extends BaseEntity> {

    String collectionName();

    T save(T entityToSave);

//    default T save(T entityToSave) {
//        MongoCollection<T> collection = database.getCollection(this.collectionName(), entityToSave.getClass());
//        collection.insertOne(entityToSave);
//        return entityToSave;
//    }

}
