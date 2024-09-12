package com.example.learnenglish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.learnenglish.database.VocabularyDatabase;
import com.example.learnenglish.model.Vocabulary;
import com.example.learnenglish.adapter.VocabularyAdapter;
import com.example.learnenglish.activity.VocabularyItemActivity;
import com.example.learnenglish.R;

import java.io.File;
import java.util.ArrayList;

public class VocabularyFragment extends Fragment {
    ArrayList<Vocabulary> vocabularyArrayList;
    VocabularyAdapter vocabularyAdapter;
    GridView gridView;
    VocabularyDatabase vocabularyDatabase;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocabulary,container,false);
        gridView = view.findViewById(R.id.gripview);

        vocabularyDatabase = new VocabularyDatabase(getActivity());
        File database = getActivity().getDatabasePath(VocabularyDatabase.DBNAME);
        if(!database.exists()) {
            vocabularyDatabase.getReadableDatabase();
            if(vocabularyDatabase.copyDatabase(getActivity())) {
                Toast.makeText(getActivity(), "Copy database success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Copy data error", Toast.LENGTH_SHORT).show();
            }
        }

        vocabularyArrayList = new ArrayList<>();
        vocabularyArrayList = vocabularyDatabase.getListVocabulary();
        vocabularyAdapter= new VocabularyAdapter(getActivity(),vocabularyArrayList, R.layout.content_layout);
        gridView.setAdapter(vocabularyAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), VocabularyItemActivity.class);
                intent.putExtra("position", vocabularyArrayList.get(position));
                intent.putExtra("level", 0); // Chọn mặc định level 0, hoặc chọn level theo yêu cầu
                startActivity(intent);
            }
        });

        return view;
    }
}
