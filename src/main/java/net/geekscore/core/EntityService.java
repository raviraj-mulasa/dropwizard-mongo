package net.geekscore.core;

public interface EntityService<T extends BaseEntity> extends Loggable {
    T save(T entityToSave);
    T findById(String id);

}
