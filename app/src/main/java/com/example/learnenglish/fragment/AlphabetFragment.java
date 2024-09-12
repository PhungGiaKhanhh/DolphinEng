package com.example.learnenglish.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.learnenglish.adapter.AlphabetAdapter;
import com.example.learnenglish.database.AlphabetDatabase;
import com.example.learnenglish.listener.OnQuestionCallbackAlphabet;
import com.example.learnenglish.model.Alphabet;

import com.example.learnenglish.R;

import java.util.ArrayList;
import java.util.List;

public class AlphabetFragment extends Fragment implements OnQuestionCallbackAlphabet {
    AlphabetDatabase database;
    AlphabetAdapter adapter;
    GridView gridView;
    List<Alphabet> alphabetList;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learnalphabet,container,false);
        gridView = view.findViewById(R.id.gridviewLearnAlphabet);
        alphabetList = new ArrayList<>();
        database = new AlphabetDatabase(getActivity());
        alphabetList = database.getListAlphabet();
        adapter = new AlphabetAdapter(getActivity(),alphabetList,R.layout.stream_item_alphabet);
        adapter.setmListener(this);
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClickQuestion(Alphabet alphabet) {
        mediaPlayer = MediaPlayer.create(getActivity(), getResourcesIdFromName(alphabet.getSoundAlphabet()));
        mediaPlayer.start();
    }

    @RawRes
    private int getResourcesIdFromName(String name) {
        return getResources().getIdentifier(name, "raw", getActivity().getPackageName());
    }

}
