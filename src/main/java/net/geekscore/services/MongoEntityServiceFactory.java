package net.geekscore.services;

import net.geekscore.core.BaseEntity;
import net.geekscore.mongo.MongoEntityService;
import org.glassfish.hk2.api.Factory;

public class MongoEntityServiceFactory<T extends BaseEntity> implements Factory<MongoEntityService<T>> {


    @Override
    public MongoEntityService<T> provide() {
        return null;
    }

    @Override
    public void dispose(MongoEntityService<T> instance) {

    }
}
