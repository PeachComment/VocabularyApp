package de.peachcomment.vocabularyapp.model.manager;

import java.util.List;

import de.peachcomment.vocabularyapp.model.cache.Cache;
import de.peachcomment.vocabularyapp.model.cache.entry.CacheEntry;
import de.peachcomment.vocabularyapp.model.cache.entry.LruCacheEntry;
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

    public T getObjectById(Long id) {
        Cache cache = getCache();
        CacheEntry entry = getCache().getEntryById(id);
        if (entry != null) {
            return (T) entry.getEntry();
        } else {
            T object = (T) getDatabase().searchObjectById(id);
            cache.addEntry(id, new LruCacheEntry(object, object));
            return object;
        }
    }

    public abstract Cache getCache();

    public abstract Database getDatabase();

}
