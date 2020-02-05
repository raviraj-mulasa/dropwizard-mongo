package net.geekscore.core.annotations;


import java.lang.annotation.*;


/**
 * Defines an index
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface Index {
    /**
     * @return List of fields to include in the index.  At least one field must be defined unless defining a text index. Use of this
     * field implies use of {@link #options()} and any options defined directly on this annotation will be ignored.
     */
    Field[] fields() default {};

    /**
     * @return Options to apply to the index.  Use of this field will ignore any of the deprecated options defined on {@link Index}
     * directly.
     */
    IndexOptions options() default @IndexOptions();

}
