package de.peachcomment.vocabularyapp.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import de.peachcomment.vocabularyapp.R;
import de.peachcomment.vocabularyapp.model.Vocabulary;
import de.peachcomment.vocabularyapp.model.persistence.sqlite.VocabularySQLiteDatabase;
import de.peachcomment.vocabularyapp.view.dialog.CancelEditingVocabularyDialogBuilder;

public class VocabularyActivity extends AppCompatActivity {

    private Vocabulary vocabulary;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        Intent intent = getIntent();
        this.vocabulary = (Vocabulary) intent.getSerializableExtra("Vocabulary");

        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);
        wordEditText.setText(vocabulary.getWord());

        if (this.vocabulary.isNew()) {
            setWordEditable(true);
        } else {
            setWordEditable(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vocabulary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit_vocabulary) {
            setWordEditable(true);
        } else if (id == R.id.action_cancel) {
            setWordEditable(false);
            showWarningOnBackOptionsItemSelected();
            closeKeyPad();
        } else if (id == R.id.action_save) {
            setWordEditable(false);
            saveVocabulary();
            closeKeyPad();
        }

        invalidateOptionsMenu();

        return true;
    }

    private void showWarningOnBackOptionsItemSelected() {
        CancelEditingVocabularyDialogBuilder alertDialogBuilder = new CancelEditingVocabularyDialogBuilder(
                this) {
            @Override
            public void onPositiveButtonClick(DialogInterface dialog, int id) {
                if (vocabulary.isNew()) {
                    finish();
                } else {
                    isEditMode = false;
                    invalidateOptionsMenu();
                }
            }

            @Override
            public void onNegativeButtonClick(DialogInterface dialog, int id) {
                dialog.cancel();
                setWordEditable(true);
            }
        };

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void saveVocabulary() {
        VocabularySQLiteDatabase db = new VocabularySQLiteDatabase(VocabularyActivity.this);
        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);
        Editable text = wordEditText.getText();
        if (text != null) {
            this.vocabulary.setWord(text.toString());
            if (this.vocabulary.isNew()) {
                long id = db.insertObject(vocabulary);
                this.vocabulary.setId(id);
            } else {
                Long id = this.vocabulary.getId();
                db.updateObject(this.vocabulary);
            }
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
        CancelEditingVocabularyDialogBuilder alertDialogBuilder = new CancelEditingVocabularyDialogBuilder(this) {
            @Override
            public void onPositiveButtonClick(DialogInterface dialog, int id) {
                VocabularyActivity.super.onBackPressed();
            }

            @Override
            public void onNegativeButtonClick(DialogInterface dialog, int id) {

            }
        };

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void setWordEditable(boolean isEditable) {
        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);
        isEditMode = isEditable;
        wordEditText.setFocusable(isEditable);
        wordEditText.setFocusableInTouchMode(isEditable);
        wordEditText.setClickable(isEditable);
    }

}
