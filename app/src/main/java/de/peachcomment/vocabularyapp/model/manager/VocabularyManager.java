package de.peachcomment.vocabularyapp.model.manager;

import android.content.Context;

import de.peachcomment.vocabularyapp.model.Vocabulary;
import de.peachcomment.vocabularyapp.model.cache.Cache;
import de.peachcomment.vocabularyapp.model.cache.LruCache;
import de.peachcomment.vocabularyapp.model.persistence.Database;
import de.peachcomment.vocabularyapp.model.persistence.sqlite.VocabularySQLiteDatabase;

/**
 * Created by PeachComment on 09.10.2016.
 */
public class VocabularyManager extends ObjectManager<Vocabulary> {

    private Cache vocabularyCache;
    private Database vocabularyDatabase;

    public VocabularyManager(Context context) {
        this.vocabularyCache = new LruCache();
        this.vocabularyDatabase = new VocabularySQLiteDatabase(context);
    }

    @Override
    public Cache getCache() {
        return null;
    }

    @Override
    public Database getDatabase() {
        return null;
    }

}
