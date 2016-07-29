package de.peachcomment.vocabularyapp.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PeachComment on 29.07.2016.
 */
public class VocabularyDatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION_1 = 1;
    private static final int CURRENT_VERSION = VERSION_1;

    public VocabularyDatabaseOpenHelper(Context context, String name) {
        super(context, name, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableVocabulary(db);
        createTableTranslation(db);
    }

    private void createTableVocabulary(SQLiteDatabase db) {
        String sql = "CREATE TABLE vocabulary " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "word VARCHAR(100) NOT NULL UNIQUE, " +
                "created_on DATE NOT NULL, " +
                "last_edited_on DATE NOT NULL)";
        db.execSQL(sql);
    }

    private void createTableTranslation(SQLiteDatabase db) {
        String sql = "CREATE TABLE translation " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "vocabulary_id INTEGER NOT NULL, " +
                "number INTEGER NOT NULL, " +
                "created_on DATE NOT NULL, " +
                "last_edited_on DATE NOT NULL, " +
                "FOREIGN KEY(vocabulary_id) REFERENCES vocabulary(_id))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
