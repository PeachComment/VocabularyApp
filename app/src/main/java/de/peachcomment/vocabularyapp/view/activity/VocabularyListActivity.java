package de.peachcomment.vocabularyapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import de.peachcomment.vocabularyapp.R;
import de.peachcomment.vocabularyapp.model.Vocabulary;
import de.peachcomment.vocabularyapp.model.persistence.sqlite.VocabularySQLiteDatabase;

public class VocabularyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_list);
        createVocabularyList();
    }

    private void createVocabularyList() {
        VocabularySQLiteDatabase db = new VocabularySQLiteDatabase(this);
        List<Vocabulary> vocabularies = db.searchAllObjects();
        Vocabulary[] vocabularyArray = new Vocabulary[vocabularies.size()];
        for (int i = 0; i < vocabularies.size(); i++) {
            vocabularyArray[i] = vocabularies.get(i);
        }

        final ListView vocabularyList = (ListView) findViewById(R.id.vocabularyListView);
        String[] displayColumns = new String[]{"word"};
        int[] displayViews = new int[]{R.id.textViewWord};

        // SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.vocabulary_list_entry, cursor, displayColumns, displayViews, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        ArrayAdapter adapter = new VocabularyAdapter(this, R.layout.vocabulary_list_entry, R.id.textViewWord, vocabularyArray);

        vocabularyList.setAdapter(adapter);

        vocabularyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vocabulary itemAtPosition = (Vocabulary) vocabularyList.getItemAtPosition(position);
                //Toast.makeText(VocabularyListActivity.this, "" + position + ": " + itemAtPosition.getWord(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VocabularyListActivity.this, VocabularyActivity.class);
                intent.putExtra("Vocabulary", itemAtPosition);
                startActivityForResult(intent, 1);
            }
        });
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
            intent.putExtra("Vocabulary", new Vocabulary());
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent refresh = new Intent(this, VocabularyListActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }
}
