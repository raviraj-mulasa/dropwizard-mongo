package net.geekscore.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import net.geekscore.core.BaseEntity;
import net.geekscore.core.EntityService;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;

public interface MongoEntityService<T extends BaseEntity> extends EntityService<T> {

    MongoCollection<T> collection();

    @Override
    default T save(T entityToSave) {
        entityToSave.setVersion(entityToSave.getVersion() + 1);
        collection().insertOne(entityToSave);
        return entityToSave;
    }

    @Override
    default T findById(String id) {
        FindIterable<T> result = collection().find(eq("_id", new ObjectId(id)));
        return result.first();
    }
}
