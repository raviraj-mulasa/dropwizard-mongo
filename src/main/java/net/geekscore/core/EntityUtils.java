package net.geekscore.core;

import net.geekscore.core.annotations.Entity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public final class EntityUtils {
    private EntityUtils(){}

    private static final Logger logger = LoggerFactory.getLogger(EntityUtils.class);

    public static String entityName(Class<? extends BaseEntity> entityClass) {
        return StringUtils.defaultIfBlank (
                entityClass.getAnnotation(Entity.class).name()
                , entityClass.getSimpleName()
        );
    }

    public static Class<? extends BaseEntity>  baseEntityClassFromType(Type baseEntityType) {
        Class<? extends BaseEntity> entityClass =  null;
        String baseEntityTypeName = baseEntityType.getTypeName();
        logger.debug("BaseEntity type parameter:{} used in  resolving BaseEntity class", baseEntityTypeName);
        try {
            entityClass = Class.forName(baseEntityTypeName).asSubclass(BaseEntity.class);
        } catch (ClassNotFoundException e) {
            logger.error("Failed resolving BaseEntity class using BaseEntity type parameter:{}  with error:{}"
                    , baseEntityTypeName
                    , e.getMessage()
                    , e
            );
        }

        return entityClass;
    }
}
