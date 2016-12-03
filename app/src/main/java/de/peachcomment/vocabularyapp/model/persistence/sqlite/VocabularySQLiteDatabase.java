package de.peachcomment.vocabularyapp.model.persistence.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.peachcomment.vocabularyapp.model.Vocabulary;
import de.peachcomment.vocabularyapp.model.persistence.Database;

/**
 * Created by PeachComment on 13.08.2016.
 */
public class VocabularySQLiteDatabase extends Database<Vocabulary> {

    private VocabularySQLiteDatabaseOpenHelper dbOpenHelper;

    public VocabularySQLiteDatabase(Context context) {
        this.dbOpenHelper = new VocabularySQLiteDatabaseOpenHelper(context);
    }

    @Override
    public List<Vocabulary> searchAllObjects() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("vocabulary", null, null, null, null, null, "word");
        if (cursor == null) {
            return Collections.emptyList();
        }
        List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            vocabularies.add(getVocabularyFromCursor(cursor));
        }
        return vocabularies;
    }

    @Override
    public Vocabulary searchObjectById(Long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("vocabulary", null, "WHERE _id = " + id, null, null, null, "word");
        if (cursor == null) {
            return null;
        }
        return getVocabularyFromCursor(cursor);
    }

    @Override
    public long insertObject(Vocabulary vocabulary) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", vocabulary.getWord());
        String currentTimestamp = getCurrentTimestamp();
        contentValues.put("timestamp_insert", currentTimestamp);
        contentValues.put("timestamp_last_update", currentTimestamp);
        SQLiteDatabase db = getReadableDatabase();
        return db.insert("vocabulary", null, contentValues);
    }

    @Override
    public void updateObject(Vocabulary vocabulary) {
        Long id = vocabulary.getId();
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", vocabulary.getWord());
        String currentTimestamp = getCurrentTimestamp();
        contentValues.put("timestamp_last_update", currentTimestamp);
        SQLiteDatabase db = getWritableDatabase();
        db.update("vocabulary", contentValues, "_id= ? ", new String[]{id.toString()});
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

    private Vocabulary getVocabularyFromCursor(Cursor cursor) {
        Long id = cursor.getLong(cursor.getColumnIndex("_id"));
        String word = cursor.getString(cursor.getColumnIndex("word"));
        Date timestampInsert = new Date(cursor.getLong(cursor.getColumnIndex("timestamp_insert")));
        Date timestampLastUpdate = new Date(cursor.getLong(cursor.getColumnIndex("timestamp_last_update")));
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setId(id);
        vocabulary.setWord(word);
        vocabulary.setTimestampInsert(timestampInsert);
        vocabulary.setTimestampLastUpdate(timestampLastUpdate);
        return vocabulary;
    }

}
