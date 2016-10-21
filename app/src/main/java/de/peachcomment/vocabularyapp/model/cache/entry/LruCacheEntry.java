package de.peachcomment.vocabularyapp.model.cache.entry;

/**
 * Created by PeachComment on 08.10.2016.
 */
public class LruCacheEntry<T> extends CacheEntry<T> {

    public LruCacheEntry(T entry, T entryInDatabase) {
        super(entry, entryInDatabase);
    }
}
