package de.peachcomment.vocabularyapp.model.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by PeachComment on 13.08.2016.
 */
public class VocabularyDatabase {

    private VocabularyDatabaseOpenHelper dbOpenHelper;

    public VocabularyDatabase(Context context) {
        this.dbOpenHelper = new VocabularyDatabaseOpenHelper(context);
    }

    public Cursor searchAllVocabularies() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query("vocabulary", null, null, null, null, null, "word");
    }

    private SQLiteDatabase getReadableDatabase() {
        return this.dbOpenHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        return this.dbOpenHelper.getWritableDatabase();
    }

}
