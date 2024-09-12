package com.example.learnenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;  // Thêm import cho ImageView
import com.example.learnenglish.R;
import android.support.v7.app.AppCompatActivity;

public class SelectUnitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_unit);

        // Xác định và thiết lập ImageView cho nút quay lại
        ImageView imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectUnitActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Xác định các nút unit và thiết lập sự kiện click
        Button unit1Button = findViewById(R.id.unit1_button);
        Button unit2Button = findViewById(R.id.unit2_button);
        Button unit3Button = findViewById(R.id.unit3_button);
        Button unit4Button = findViewById(R.id.unit4_button);
        Button unit5Button = findViewById(R.id.unit5_button);
        Button unit6Button = findViewById(R.id.unit6_button);
        Button unit7Button = findViewById(R.id.unit7_button);
        Button unit8Button = findViewById(R.id.unit8_button);

        unit1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(1);
            }
        });

        unit2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(2);
            }
        });

        unit3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(3);
            }
        });

        unit4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(4);
            }
        });

        unit5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(5);
            }
        });

        unit6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(6);
            }
        });

        unit7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(7);
            }
        });

        unit8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity(8);
            }
        });
    }

    private void startQuizActivity(int unit) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("unit", unit);
        startActivity(intent);
    }
}
