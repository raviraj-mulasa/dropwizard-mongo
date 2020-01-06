package net.geekscore.core;

public interface Repository<T extends BaseEntity> extends Loggable {
    T save(T entityToSave);
    T findById(String id);

}
