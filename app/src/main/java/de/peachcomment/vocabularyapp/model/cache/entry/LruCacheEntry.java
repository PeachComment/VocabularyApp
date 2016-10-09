package de.peachcomment.vocabularyapp.model.cache.entry;

/**
 * Created by PeachComment on 08.10.2016.
 */
public class LruCacheEntry<T> extends CacheEntry<T> {

    private T entry;
    private T entryInDatabase;

    public LruCacheEntry(T entry, T entryInDatabase) {
        this.entry = entry;
        this.entryInDatabase = entryInDatabase;
    }

    public T getEntry() {
        return this.entry;
    }

    public boolean isNew() {
        return entryInDatabase == null;
    }

    public boolean isChanged() {
        return entry.equals(entryInDatabase);
    }

}
