package de.peachcomment.vocabularyapp.model.cache;

import de.peachcomment.vocabularyapp.model.cache.entry.CacheEntry;

/**
 * Created by PeachComment on 08.10.2016.
 */
public abstract class Cache {

    public abstract void addEntry(Long id, CacheEntry entry);

    public abstract CacheEntry getEntryById(Long id);

}
