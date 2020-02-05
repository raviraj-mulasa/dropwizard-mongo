package net.geekscore.core;

public interface EntityStore<T extends BaseEntity> extends Loggable {
    T upsert(T entityToSave);
    T findById(String id);
    T deleteById(String id, boolean hardDelete);
    default T deleteById(String id) {
        return this.deleteById(id, Boolean.FALSE);
    }

}
