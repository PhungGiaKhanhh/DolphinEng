package com.example.learnenglish.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnenglish.listener.OnQuestionCallbackVocabularyItem;
import com.example.learnenglish.model.VocabularyItem;
import com.example.learnenglish.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VocabularyItemAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<VocabularyItem> itemList;
    private OnQuestionCallbackVocabularyItem mListener;


    public VocabularyItemAdapter(Context context, int layout, List<VocabularyItem> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    public void setListener(OnQuestionCallbackVocabularyItem listener) {
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        ImageView img_Item;
        TextView txt_TienganhItem;
        TextView txt_TiengvietItem;
        ImageButton imageButton_Loa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.stream_item_vocabulary, null);
            holder.img_Item=(ImageView) convertView.findViewById(R.id.image_item);
            holder.txt_TienganhItem=(TextView) convertView.findViewById(R.id.textview_Tutienganh);
            holder.txt_TiengvietItem=(TextView) convertView.findViewById(R.id.textview_Tutiengviet);
            holder.imageButton_Loa=(ImageButton) convertView.findViewById(R.id.imageButton_sound);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        final  VocabularyItem vocabularyItem= itemList.get(position);
        InputStream imss = null;
        try {
            imss = context.getAssets().open( "img/"+vocabularyItem.getImageItem() +".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(imss, null);
        holder.img_Item.setImageDrawable(d);
        holder.txt_TienganhItem.setText(vocabularyItem.getEnglishWordItem());
        holder.txt_TiengvietItem.setText(vocabularyItem.getVietnameseWordItem());
        holder.imageButton_Loa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onClickQuestion(vocabularyItem);
                }
            }
        });
        return convertView;
    }
}
