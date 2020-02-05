package net.geekscore.mongo;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.BaseEntity;
import net.geekscore.core.EntityUtils;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CollectionInjectResolver implements InjectionResolver<Collection> {

    private static final Logger logger = LoggerFactory.getLogger(CollectionInjectResolver.class);

    private static final Map<Class<? extends BaseEntity>, MongoCollection<? extends BaseEntity>>
            MONGO_COLLECTION_HASH_MAP = new HashMap<>();

    @Override
    public Object resolve(Injectee injectee, ServiceHandle<?> root) {
        MongoCollection<? extends BaseEntity> mongoCollection = null;
        Type injecteeType = injectee.getRequiredType();
        if(injecteeType instanceof ParameterizedType) {
            ParameterizedType injecteeParameterizedType = (ParameterizedType) injecteeType;
            Type rawType = injecteeParameterizedType.getRawType();
            if (MongoCollection.class == rawType) {
                Type[] actualTypeArguments = injecteeParameterizedType.getActualTypeArguments();
                if(actualTypeArguments == null || actualTypeArguments.length < 1) {
                    logger.error("BaseEntity type parameter missing to resolve @Collection annotation");
                    return null;
                }
                Type baseEntityTypeParameter = actualTypeArguments[0];
                Class<? extends BaseEntity> baseEntityClass = EntityUtils.baseEntityClassFromType(baseEntityTypeParameter);
                Objects.requireNonNull(baseEntityClass, "Entity class cannot be null!");
                mongoCollection = this.mongoCollection(baseEntityClass);
            }
        }
        return mongoCollection;
    }

    @Override
    public boolean isConstructorParameterIndicator() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isMethodParameterIndicator() {
        return Boolean.TRUE;
    }

    private MongoCollection<? extends BaseEntity> mongoCollection(Class<? extends BaseEntity> baseEntityClass) {
        if(!MONGO_COLLECTION_HASH_MAP.containsKey(baseEntityClass)) {
            String collectionName = EntityUtils.entityName(baseEntityClass);
            MongoCollection<? extends BaseEntity> mongoCollection =
                    MongoDB.INSTANCE.database().getCollection(
                        collectionName
                        , baseEntityClass
                    );
            MONGO_COLLECTION_HASH_MAP.put(baseEntityClass, mongoCollection);
        }
        return MONGO_COLLECTION_HASH_MAP.get(baseEntityClass);
    }
}

