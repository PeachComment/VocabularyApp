package de.peachcomment.vocabularyapp.model.cache;

import java.util.LinkedHashMap;

import de.peachcomment.vocabularyapp.model.cache.entry.CacheEntry;

/**
 * Created by PeachComment on 08.10.2016.
 */
public class LruCache extends Cache {

    private LinkedHashMap<Long, CacheEntry> map;

    private static final int INITIAL_CAPACITY = 50;
    private static final float LOAD_FACTOR = 0.75f;
    private static final int MAX_ENTRIES = 100;

    public LruCache() {
        initMap();
    }

    private void initMap() {
        this.map = new LinkedHashMap<Long, CacheEntry>(INITIAL_CAPACITY, LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Entry<Long, CacheEntry> eldest) {
                return LruCache.this.map.size() > MAX_ENTRIES;
            }
        };
    }

    @Override
    public void addEntry(Long id, CacheEntry entry) {
        this.map.put(id, entry);
    }

    @Override
    public CacheEntry getEntryById(Long id) {
        return this.map.get(id);
    }
}
