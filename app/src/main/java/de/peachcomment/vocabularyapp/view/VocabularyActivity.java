package de.peachcomment.vocabularyapp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import de.peachcomment.vocabularyapp.R;

public class VocabularyActivity extends AppCompatActivity {

    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        Intent intent = getIntent();
        String isNewVocabulary = intent.getStringExtra("isNewVocabulary");
        if ("true".equals(isNewVocabulary)) {
            setWordEditable(true);
        } else {
            setWordEditable(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vocabulary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);

        if (id == R.id.action_edit_vocabulary) {
            setWordEditable(true);
        } else if (id == R.id.action_cancel) {
            setWordEditable(false);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            alertDialogBuilder.setTitle("Your Title");

            alertDialogBuilder
                    .setMessage("Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // TODO
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
        } else if (id == R.id.action_save) {
            setWordEditable(false);
        }

        invalidateOptionsMenu();

        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem actionEditVocabulary = menu.findItem(R.id.action_edit_vocabulary);
        actionEditVocabulary.setVisible(!isEditMode);
        MenuItem actionCancel = menu.findItem(R.id.action_cancel);
        actionCancel.setVisible(isEditMode);
        MenuItem actionSave = menu.findItem(R.id.action_save);
        actionSave.setVisible(isEditMode);
        MenuItem actionNewTranslation = menu.findItem(R.id.action_new_translation);
        actionNewTranslation.setVisible(isEditMode);
        return true;
    }

    private void setWordEditable(boolean isEditable) {
        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);
        isEditMode = true;
        wordEditText.setFocusable(isEditable);
        wordEditText.setFocusableInTouchMode(isEditable);
        wordEditText.setClickable(isEditable);
    }
}
