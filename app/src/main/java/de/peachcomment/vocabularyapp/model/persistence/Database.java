package de.peachcomment.vocabularyapp.model.persistence;

import java.util.List;

/**
 * Created by PeachComment on 08.10.2016.
 */
public abstract class Database<T> {

    public abstract List<T> searchAllObjects();

    public abstract T searchObjectById(Long id);

    public abstract long insertObject(T object);

}
