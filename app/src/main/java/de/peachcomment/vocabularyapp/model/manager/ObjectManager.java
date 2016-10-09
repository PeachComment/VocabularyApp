package de.peachcomment.vocabularyapp.model.manager;

import java.util.List;

import de.peachcomment.vocabularyapp.model.cache.Cache;
import de.peachcomment.vocabularyapp.model.persistence.Database;

/**
 * Created by PeachComment on 08.10.2016.
 */
public abstract class ObjectManager<T> {

    public long serializeObject(T object) {
        return getDatabase().insertObject(object);
    }

    public List<T> getAllObjects() {
        return getDatabase().searchAllObjects();
    }

    public T getObjectById(long id) {
        return (T) getDatabase().searchObjectById(id);
    }

    public abstract Cache getCache();

    public abstract Database getDatabase();

}
