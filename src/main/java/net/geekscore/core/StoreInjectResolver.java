package net.geekscore.core;

import com.mongodb.client.MongoCollection;
import net.geekscore.mongo.Collection;
import net.geekscore.mongo.MongoDB;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class StoreInjectResolver implements InjectionResolver<Collection> {

    @Override
    public Object resolve(Injectee injectee, ServiceHandle<?> root) {
        MongoCollection<? extends BaseEntity> mongoCollection = null;
        Type injecteeType = injectee.getRequiredType();
        if(injecteeType instanceof ParameterizedType) {
            ParameterizedType injecteeParameterizedType = (ParameterizedType) injecteeType;
            Type rawType = injecteeParameterizedType.getRawType();
            if (MongoCollection.class == rawType) {
                Type[] actualTypeArguments = injecteeParameterizedType.getActualTypeArguments();

                try {
                    System.out.println(actualTypeArguments[0].getTypeName());
                    Class<? extends BaseEntity> entityClass = Class.forName(actualTypeArguments[0].getTypeName()).asSubclass(BaseEntity.class);
                    mongoCollection= MongoDB.INSTANCE.database().getCollection("employer", entityClass);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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
}
