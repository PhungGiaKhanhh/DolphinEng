package com.example.learnenglish.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learnenglish.listener.OnQuestionCallbackAlphabet;
import com.example.learnenglish.model.Alphabet;
import com.example.learnenglish.R;
import java.util.List;

public class AlphabetAdapter extends BaseAdapter {
    private Context context;
    private List<Alphabet> alphabetList;
    private int layout;
    private OnQuestionCallbackAlphabet mListener;

    public AlphabetAdapter(Context context, List<Alphabet> alphabetList, int layout) {
        this.context = context;
        this.alphabetList = alphabetList;
        this.layout = layout;
    }

    public void setmListener(OnQuestionCallbackAlphabet mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return alphabetList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.stream_item_alphabet,parent,false);
            holder.nameAlphabet = convertView.findViewById(R.id.tvAlphabet);
            holder.spellAlphabet = convertView.findViewById(R.id.tvSpell);
            holder.constraintLayout = convertView.findViewById(R.id.bg_alphabet);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Alphabet alphabet = alphabetList.get(position);
        holder.nameAlphabet.setText(alphabet.getNameAlphabet());
        holder.spellAlphabet.setText(alphabet.getSpellAlphabet());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    holder.constraintLayout.setBackgroundColor(Color.GRAY);
                    mListener.onClickQuestion(alphabet);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder{
        TextView nameAlphabet;
        TextView spellAlphabet;
        ConstraintLayout constraintLayout;
    }

}
