package com.example.learnenglish.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.example.learnenglish.model.Question;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Arrays;
import java.util.Collections;

import android.content.Intent;

public class QuizActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private Button btnChoose1, btnChoose2, btnChoose3;
    private ImageView questionImage;
    private TextView questionCounter;
    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        btnChoose1 = findViewById(R.id.btn_choose1);
        btnChoose2 = findViewById(R.id.btn_choose2);
        btnChoose3 = findViewById(R.id.btn_choose3);
        questionImage = findViewById(R.id.question_image);
        questionCounter = findViewById(R.id.question_counter);
        ImageView imageBack = findViewById(R.id.image_back);

        int unit = getIntent().getIntExtra("unit", 1);

        loadQuestions(unit);
        displayQuestion(currentQuestionIndex);

        btnChoose1.setOnClickListener(this::clickChoose);
        btnChoose2.setOnClickListener(this::clickChoose);
        btnChoose3.setOnClickListener(this::clickChoose);

        imageBack.setOnClickListener(v -> finish());
    }

    private void loadQuestions(int unitId) {
        questions = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("quiz_data.json");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray units = jsonObject.getJSONArray("units");

            for (int i = 0; i < units.length(); i++) {
                JSONObject unit = units.getJSONObject(i);
                if (unit.getInt("unit_id") == unitId) {
                    JSONArray questionsArray = unit.getJSONArray("questions");
                    for (int j = 0; j < questionsArray.length(); j++) {
                        JSONObject questionObject = questionsArray.getJSONObject(j);
                        String imageFileName = questionObject.getString("image");
                        JSONArray optionsArray = questionObject.getJSONArray("options");
                        String[] options = new String[optionsArray.length()];

                        for (int k = 0; k < optionsArray.length(); k++) {
                            options[k] = optionsArray.getString(k);
                        }
                        String correctOption = questionObject.getString("correct_option");
                        int correctOptionIndex = -1;

                        for (int k = 0; k < options.length; k++) {
                            if (options[k].equals(correctOption)) {
                                correctOptionIndex = k;
                                break;
                            }
                        }

                        questions.add(new Question(imageFileName, options, correctOptionIndex));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayQuestion(int index) {
        if (index >= questions.size()) {
            // End of quiz, navigate to ResultActivity
            Intent resultIntent = new Intent(QuizActivity.this, ResultActivity.class);
            resultIntent.putExtra("correctAnswers", score);
            resultIntent.putExtra("totalQuestions", questions.size());
            startActivity(resultIntent);
            finish(); // Close QuizActivity
            return;
        }

        Question question = questions.get(index);
        String imageFileName = question.getImageFileName();

        // Load image from assets/img
        try {
            InputStream is = getAssets().open("img/" + imageFileName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            if (bitmap != null) {
                questionImage.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "Failed to decode image: " + imageFileName, Toast.LENGTH_SHORT).show();
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Image not found: " + imageFileName, Toast.LENGTH_SHORT).show();
        }

        // Shuffle answers
        List<String> options = new ArrayList<>(Arrays.asList(question.getOptions()));
        Collections.shuffle(options);

        // Set answers to buttons
        btnChoose1.setText(options.get(0));
        btnChoose2.setText(options.get(1));
        btnChoose3.setText(options.get(2));

        // Determine correct answer
        correctAnswer = question.getOptions()[question.getCorrectAnswerIndex()];
        if (options.get(0).equals(correctAnswer)) {
            this.correctAnswer = btnChoose1.getText().toString();
        } else if (options.get(1).equals(correctAnswer)) {
            this.correctAnswer = btnChoose2.getText().toString();
        } else {
            this.correctAnswer = btnChoose3.getText().toString();
        }

        questionCounter.setText("Question " + (index + 1) + "/" + questions.size());
    }

    public void clickChoose(View view) {
        Button clickedButton = (Button) view;
        String selectedAnswer = clickedButton.getText().toString();

        if (selectedAnswer.equals(correctAnswer)) {
            clickedButton.setBackgroundResource(R.drawable.background_btn_correct);
            score++;
        } else {
            clickedButton.setBackgroundResource(R.drawable.background_btn_incorrect);
            highlightCorrectAnswer();
        }

        // Disable buttons to prevent multiple clicks
        disableButtons();

        // Move to next question after delay
        new Handler().postDelayed(() -> {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);

            // Reset button colors
            btnChoose1.setBackgroundResource(R.drawable.background_btn_choose);
            btnChoose2.setBackgroundResource(R.drawable.background_btn_choose);
            btnChoose3.setBackgroundResource(R.drawable.background_btn_choose);

            // Enable buttons again
            enableButtons();
        }, 2000);
    }

    private void highlightCorrectAnswer() {
        if (btnChoose1.getText().toString().equals(correctAnswer)) {
            btnChoose1.setBackgroundResource(R.drawable.background_btn_correct);
        }
        if (btnChoose2.getText().toString().equals(correctAnswer)) {
            btnChoose2.setBackgroundResource(R.drawable.background_btn_correct);
        }
        if (btnChoose3.getText().toString().equals(correctAnswer)) {
            btnChoose3.setBackgroundResource(R.drawable.background_btn_correct);
        }
    }

    private void disableButtons() {
        btnChoose1.setEnabled(false);
        btnChoose2.setEnabled(false);
        btnChoose3.setEnabled(false);
    }

    private void enableButtons() {
        btnChoose1.setEnabled(true);
        btnChoose2.setEnabled(true);
        btnChoose3.setEnabled(true);
    }
}
