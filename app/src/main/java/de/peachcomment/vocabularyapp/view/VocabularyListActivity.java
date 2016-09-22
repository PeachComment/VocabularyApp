package de.peachcomment.vocabularyapp.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import de.peachcomment.vocabularyapp.R;
import de.peachcomment.vocabularyapp.model.persistence.VocabularyDatabase;

public class VocabularyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_list);
        createVocabularyList();
    }

    private void createVocabularyList() {
        VocabularyDatabase db = new VocabularyDatabase(this);
        Cursor cursor = db.searchAllVocabularies();

        ListView vocabularyList = (ListView) findViewById(R.id.vocabularyListView);
        String[] displayColumns = new String[]{"word"};
        int[] displayViews = new int[]{R.id.textViewWord};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.vocabulary_list_entry, cursor, displayColumns, displayViews, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        vocabularyList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vocabulary_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        if (id == R.id.action_new_vocabulary) {
            Intent intent = new Intent(VocabularyListActivity.this, VocabularyActivity.class);
            intent.putExtra("isNewVocabulary","true");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
