package de.peachcomment.vocabularyapp.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import de.peachcomment.vocabularyapp.R;

/**
 * Created by PeachComment on 09.10.2016.
 */
public abstract class CancelEditingVocabularyDialogBuilder extends AlertDialog.Builder {

    public CancelEditingVocabularyDialogBuilder(Context context) {
        super(context);
        initDialogBuilder();
    }

    private void initDialogBuilder() {
        initMessage();
        initCancelable();
        initPositiveButton();
        initNegativeButton();
    }

    private void initMessage() {
        setMessage(R.string.cancel_editing_vocabulary_question);
    }

    private void initCancelable() {
        setCancelable(false);
    }

    private void initPositiveButton() {
        setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                onPositiveButtonClick(dialog, id);
            }
        });
    }

    private void initNegativeButton() {
        setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                onNegativeButtonClick(dialog, id);
            }
        });
    }

    public abstract void onPositiveButtonClick(DialogInterface dialog, int id);

    public abstract void onNegativeButtonClick(DialogInterface dialog, int id);

}
