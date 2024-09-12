package com.example.learnenglish.model;

public class Question {
    private String imageFileName;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String imageFileName, String[] options, int correctAnswerIndex) {
        this.imageFileName = imageFileName;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
