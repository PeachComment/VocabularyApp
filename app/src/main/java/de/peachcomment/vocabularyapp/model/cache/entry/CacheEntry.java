package de.peachcomment.vocabularyapp.model.cache.entry;

/**
 * Created by Wencke on 08.10.2016.
 */
public abstract class CacheEntry<T> {

    private T entry;
    private T entryInDatabase;

    public CacheEntry(T entry, T entryInDatabase) {
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
