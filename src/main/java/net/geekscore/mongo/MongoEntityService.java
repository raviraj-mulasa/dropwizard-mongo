package net.geekscore.mongo;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.BaseEntity;
import net.geekscore.core.EntityService;

public interface MongoEntityService<T extends BaseEntity> extends EntityService<T> {

    MongoCollection<T> collection();

    @Override
    default T save(T entityToSave) {
        entityToSave.setVersion(entityToSave.getVersion() + 1);
        collection().insertOne(entityToSave);
        return entityToSave;
    }
}
