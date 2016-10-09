package de.peachcomment.vocabularyapp.view.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.peachcomment.vocabularyapp.model.Vocabulary;

/**
 * Created by PeachComment on 09.10.2016.
 */
public class VocabularyAdapter extends ArrayAdapter<Vocabulary> {

    public static final int INDEX_TEXT_VIEW = 0;

    public VocabularyAdapter(Context context, int resource, int textViewResourceId, Vocabulary[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (view instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view;
            View childView = linearLayout.getChildAt(INDEX_TEXT_VIEW);
            if (childView instanceof TextView) {
                TextView textView = (TextView) childView;
                textView.setText(getItem(position).getWord());
            }
        }
        return view;
    }

}
