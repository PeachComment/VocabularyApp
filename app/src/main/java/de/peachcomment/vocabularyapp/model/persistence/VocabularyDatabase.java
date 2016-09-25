package de.peachcomment.vocabularyapp.model.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.peachcomment.vocabularyapp.model.Vocabulary;

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

    public long insertVocabulary(Vocabulary vocabulary) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", vocabulary.getWord());
        String currentTimestamp = getCurrentTimestamp();
        contentValues.put("timestamp_insert", currentTimestamp);
        contentValues.put("timestamp_last_update", currentTimestamp);
        SQLiteDatabase db = getReadableDatabase();
        return db.insert("vocabulary", null, contentValues);
    }

    private SQLiteDatabase getReadableDatabase() {
        return this.dbOpenHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        return this.dbOpenHelper.getWritableDatabase();
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
