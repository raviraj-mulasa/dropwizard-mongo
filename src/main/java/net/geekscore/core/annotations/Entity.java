package net.geekscore.core.annotations;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * Allows marking and naming the collectionName
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Entity {
    /**
     * @return the collection name to for this entity.  Defaults to the class's simple name
     *
     * @see Class#getSimpleName()
     */
    String name() default StringUtils.EMPTY;
}

