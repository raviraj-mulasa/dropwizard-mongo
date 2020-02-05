package net.geekscore.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import net.geekscore.core.BaseEntity;
import net.geekscore.core.EntityStore;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

import static com.mongodb.client.model.Filters.eq;

public abstract class MongoEntityStore<T extends BaseEntity> implements EntityStore<T> {

    private final MongoCollection<T> collection;

    public MongoEntityStore(@NotNull MongoCollection<T> collection) {
        this.collection = collection;
    }


    @Override
    public T upsert(T entityToSave) {
        entityToSave.setVersion(entityToSave.getVersion() + 1);
        collection.insertOne(entityToSave);
        return entityToSave;
    }

    @Override
    public T findById(String id) {
        FindIterable<T> result = collection.find(eq("_id", new ObjectId(id)));
        return result.first();
    }

    @Override
    public T deleteById(String id, boolean hardDelete) {
        return collection.findOneAndDelete(eq("_id", new ObjectId(id)));
    }
}