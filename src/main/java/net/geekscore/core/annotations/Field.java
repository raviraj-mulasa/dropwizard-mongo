package net.geekscore.core.annotations;

import java.lang.annotation.*;

/**
 * Define a field to be used in an index;
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface Field {
    /**
     * @return "Direction" of the indexing.  Defaults to {@link IndexType#ASC}.
     *
     * @see IndexType
     */
    IndexType type() default IndexType.ASC;

    /**
     * @return Field name to index
     */
    String value();

    /**
     * @return The weight to use when creating a text index.  This value only makes sense when direction is {@link IndexType#TEXT}
     */
    int weight() default -1;
}
