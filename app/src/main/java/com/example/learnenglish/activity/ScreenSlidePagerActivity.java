package com.example.learnenglish.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.learnenglish.adapter.CheckAnswerAdapter;
import com.example.learnenglish.database.TestItemDatabase;
import com.example.learnenglish.fragment.ScreenSlidePageFragment;
import com.example.learnenglish.model.Question;
import com.example.learnenglish.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 10;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;
    TestItemDatabase testItemDatabase;
    ArrayList<Question> list_question;
    private TextView txtKiemtra, tvTimer, tvXemdiem;
    CounterClass timer;
    public int checkAns=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_pager);
        txtKiemtra= (TextView) findViewById(R.id.tvKiemTra);
        tvXemdiem=(TextView) findViewById(R.id.tvScore);
        tvTimer=(TextView) findViewById(R.id.tvTimer);
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        timer= new CounterClass(60*1000, 1000);
        testItemDatabase= new TestItemDatabase(this);
        list_question= new ArrayList<>();
        Intent intent= getIntent();
        int vitri= intent.getIntExtra("viTri",124);
        list_question= testItemDatabase.getListQuestion(vitri+1);
        txtKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDapan();
            }
        });
        tvXemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScreenSlidePagerActivity.this, TestDoneActivity.class);
                startActivity(intent);
                truyenDuLieu();
            }

        });
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        timer.start();
    }

    public void truyenDuLieu(){
        Intent intent1=new Intent(ScreenSlidePagerActivity.this,TestDoneActivity.class);
        intent1.putExtra("arr_Ques", list_question);
        startActivity(intent1);
        finish();
    }
    public ArrayList<Question> getData(){
        return list_question;
    }
    public ScreenSlidePageFragment creat(int pageNumber, int checkAnswer){
        ScreenSlidePageFragment fragment= new ScreenSlidePageFragment();
        Bundle args= new Bundle();
        args.putInt("page", pageNumber);
        args.putInt("checkAnswer", checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onBackPressed(){
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return creat(position, checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) {

                view.setAlpha(0f);

            } else if (position <= 0) {

                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                view.setAlpha(1 - position);

                view.setTranslationX(pageWidth * -position);

                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else {
                view.setAlpha(0f);
            }
        }
    }

    public void checkDapan(){
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_checkdapan);
        dialog.setTitle("Danh sách câu trả lời");
        CheckAnswerAdapter checkAnswerAdapter = new CheckAnswerAdapter(this, list_question);
        GridView gridView= (GridView) dialog.findViewById(R.id.gridview);
        gridView.setAdapter(checkAnswerAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        Button btn_huy= (Button) dialog.findViewById(R.id.button_huy);
        Button btn_ketThuc=(Button) dialog.findViewById(R.id.button_ketthuc);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                timer.cancel();
            }
        });
        btn_ketThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScreenSlidePagerActivity.this, TestDoneActivity.class);
                startActivity(intent);
                truyenDuLieu();
                timer.cancel();
                checkAns=1;
                txtKiemtra.setVisibility(View.GONE);
                tvXemdiem.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.show();
        timer.cancel();
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime);
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");
            Intent intent = new Intent(ScreenSlidePagerActivity.this, TestDoneActivity.class);
            startActivity(intent);
            truyenDuLieu();
        }
    }

}
