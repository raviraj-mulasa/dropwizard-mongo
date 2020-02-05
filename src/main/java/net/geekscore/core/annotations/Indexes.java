package net.geekscore.core.annotations;

import java.lang.annotation.*;

/**
 * Defines indexes for this entity type (on the collection)
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Indexes {
    /**
     * @return The index definitions for this entity
     */
    Index[] value();
}
