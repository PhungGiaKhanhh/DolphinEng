package com.example.learnenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learnenglish.model.Question;
import com.example.learnenglish.R;

import java.util.ArrayList;

public class CheckAnswerAdapter extends BaseAdapter{
    Context context;
    ArrayList arrayList;

    public CheckAnswerAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_dongdapan, null);
            holder.txtCau=(TextView) convertView.findViewById(R.id.textview_cau);
            holder.txtDapan=(TextView) convertView.findViewById(R.id.textview_dapan);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder) convertView.getTag();
        }
        Question question= (Question) getItem(position);
        int i= position+1;
        holder.txtCau.setText("Câu "+i+": ");
        if (question.getTraLoi().equals(question.getAnswer())) {
            holder.txtDapan.setText(question.getTraLoi() + " \u2713");
        } else {
            holder.txtDapan.setText(question.getTraLoi());
        }

        return convertView;
    }
    class ViewHolder{
        TextView txtCau, txtDapan;
    }
}