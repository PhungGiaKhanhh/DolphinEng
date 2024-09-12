package com.example.learnenglish.fragment;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.learnenglish.activity.ScreenSlidePagerActivity;
import com.example.learnenglish.model.Question;
import com.example.learnenglish.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {
    ArrayList<Question> arr_ques;
    public static final String ARGS_PAGE="page";
    public static final String ARGS_CHECKANSWER="checkAnswer";
    private int mPageNumber;
    public int checkAns;

    TextView txtNum;
    ImageView image_anh;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC;
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        txtNum= (TextView) rootView.findViewById(R.id.tvNum);
        image_anh=(ImageView) rootView.findViewById(R.id.image_question);
        radA=(RadioButton) rootView.findViewById(R.id.radA);
        radB=(RadioButton) rootView.findViewById(R.id.radB);
        radC=(RadioButton) rootView.findViewById(R.id.radC);
        radioGroup=(RadioGroup) rootView.findViewById(R.id.radGroup);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_ques= new ArrayList<>();
        ScreenSlidePagerActivity slidePagerActivity= (ScreenSlidePagerActivity) getActivity();
        arr_ques= slidePagerActivity.getData();
        mPageNumber= getArguments().getInt(ARGS_PAGE);
        checkAns= getArguments().getInt(ARGS_CHECKANSWER);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtNum.setText("Question "+ (mPageNumber+1));
        InputStream imss = null;
        try {
            imss = getActivity().getAssets().open( "img/"+arr_ques.get(mPageNumber).getImage() +".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(imss, null);
        image_anh.setImageDrawable(d);
        radA.setText(arr_ques.get(mPageNumber).getAns_1());
        radB.setText(arr_ques.get(mPageNumber).getAns_2());
        radC.setText(arr_ques.get(mPageNumber).getAns_3());
        if(checkAns!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            getCheckAns(getItem(mPageNumber).getAnswer().toString());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                arr_ques.get(mPageNumber).choiceID= checkedId;
                arr_ques.get(mPageNumber).setTraLoi(getChoiFromID(checkedId));
            }
        });
    }
    public Question getItem(int posotion){
        return arr_ques.get(posotion);
    }
    public String getChoiFromID(int ID){
        if(ID==R.id.radA){
            return "A";
        }
        else if(ID==R.id.radB){
            return "B";
        }
        else if(ID==R.id.radC){
            return "C";
        }
        return "";
    }
    private void getCheckAns(String ans){
        if(ans.equals("A")==true){
            radA.setTextColor(Color.RED);
        }
        else if(ans.equals("B")==true){
            radA.setTextColor(Color.RED);
        }
        else if(ans.equals("C")==true){
            radA.setTextColor(Color.RED);
        }
    }

}
