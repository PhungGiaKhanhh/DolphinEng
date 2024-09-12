package com.example.learnenglish.adapter;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnenglish.model.Vocabulary;
import com.example.learnenglish.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VocabularyAdapter extends BaseAdapter {
    private Context context;
    private List<Vocabulary> vocabularyList;
    private int layout;

    public VocabularyAdapter(Context context, List<Vocabulary> vocabularyList, int layout) {
        this.context = context;
        this.vocabularyList = vocabularyList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return vocabularyList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
            viewHolder.tvName = view.findViewById(R.id.name);
            viewHolder.imageContent = view.findViewById(R.id.image);
            view.setTag(viewHolder);
        } else viewHolder = (ViewHolder) view.getTag();

        Vocabulary vocabulary = vocabularyList.get(i);
        InputStream ims = null;
        try {
            Log.d("duc123", "getView: " + vocabulary.getImageVocabulary());
            ims = context.getAssets().open("img/" + vocabulary.getImageVocabulary() + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(ims, null);
        viewHolder.imageContent.setImageDrawable(d);
        viewHolder.tvName.setText(vocabulary.getNameVocabulary());
        return view;
    }

    private class ViewHolder {
        ImageView imageContent;
        TextView tvName;
    }

}
