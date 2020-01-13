package net.geekscore.core;

public interface EntityStore<T extends BaseEntity> extends Loggable {
    T save(T entityToSave);
    T findById(String id);

}
