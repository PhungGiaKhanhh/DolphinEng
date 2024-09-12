package com.example.learnenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.learnenglish.R;

public class ResultActivity extends Activity {

    private TextView txtScore;
    private Button buttonExit;
    private ImageButton buttonShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);

        // Initialize TextView for score
        txtScore = findViewById(R.id.txt_Score);

        // Initialize Buttons
        buttonExit = findViewById(R.id.Button_Thoat);
        buttonShare = findViewById(R.id.button_share);

        // Receive data from QuizActivity
        Intent intent = getIntent();
        int correctAnswers = intent.getIntExtra("correctAnswers", 0);

        // Calculate total score
        int totalScore = correctAnswers * 10; // Each correct answer is worth 10 points

        // Update TextView with total score
        txtScore.setText("Score: " + totalScore);

        // Set click listeners for buttons
        buttonExit.setOnClickListener(v -> {
            // Exit the app or navigate to another activity
            finish(); // Or use Intent to navigate to another activity if needed
        });

        buttonShare.setOnClickListener(v -> {
            // Share functionality (you can implement sharing logic here)
        });
    }
}
