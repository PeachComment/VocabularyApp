package de.peachcomment.vocabularyapp.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import de.peachcomment.vocabularyapp.R;
import de.peachcomment.vocabularyapp.model.Vocabulary;
import de.peachcomment.vocabularyapp.model.persistence.VocabularyDatabase;

public class VocabularyActivity extends AppCompatActivity {

    private Vocabulary vocabulary;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        Intent intent = getIntent();
        this.vocabulary = (Vocabulary) intent.getSerializableExtra("Vocabulary");
        if (this.vocabulary.isNew()) {
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

        // EditText wordEditText = (EditText) findViewById(R.id.wordEditText);

        if (id == R.id.action_edit_vocabulary) {
            setWordEditable(true);
        } else if (id == R.id.action_cancel) {
            setWordEditable(false);
            showWarning();
            closeKeyPad();
        } else if (id == R.id.action_save) {
            setWordEditable(false);
            saveVocabulary();
            closeKeyPad();
        }

        invalidateOptionsMenu();

        return true;
    }

    private void showWarning() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle(R.string.warning);

        alertDialogBuilder
                .setMessage(R.string.cancel_editing_vocabulary_question)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (vocabulary.isNew()) {
                            finish();
                        } else {
                            isEditMode = false;
                            invalidateOptionsMenu();
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        setWordEditable(true);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void saveVocabulary() {
        VocabularyDatabase db = new VocabularyDatabase(VocabularyActivity.this);
        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);
        Editable text = wordEditText.getText();
        if (text != null) {
            this.vocabulary.setWord(text.toString());
            long id = db.insertVocabulary(vocabulary);
            this.vocabulary.setId(id);
            this.isEditMode = false;
        }
    }

    private void closeKeyPad() {
        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(wordEditText.getWindowToken(), 0);
    }

    @Override
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

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, null);
        if (this.vocabulary.isChanged()) {
            showWarningOnBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    private void showWarningOnBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle(R.string.warning);

        alertDialogBuilder
                .setMessage(R.string.cancel_editing_vocabulary_question)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        VocabularyActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

}
