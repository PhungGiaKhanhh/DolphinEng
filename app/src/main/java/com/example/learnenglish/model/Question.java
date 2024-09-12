package com.example.learnenglish.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String image;
    private String ans_1;
    private String ans_2;
    private String ans_3;
    private String answer;
    private String baiTest;
    private String traLoi;
    public int choiceID=-1;

    public Question(int id, String image, String ans_1, String ans_2, String ans_3, String answer, String baiTest, String traLoi) {
        this.id = id;
        this.image = image;
        this.ans_1 = ans_1;
        this.ans_2 = ans_2;
        this.ans_3 = ans_3;
        this.answer = answer;
        this.baiTest = baiTest;
        this.traLoi=traLoi;
    }

    public String getTraLoi() {
        return traLoi;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAns_1() {
        return ans_1;
    }

    public void setAns_1(String ans_1) {
        this.ans_1 = ans_1;
    }

    public String getAns_2() {
        return ans_2;
    }

    public void setAns_2(String ans_2) {
        this.ans_2 = ans_2;
    }

    public String getAns_3() {
        return ans_3;
    }

    public void setAns_3(String ans_3) {
        this.ans_3 = ans_3;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getBaiTest() {
        return baiTest;
    }

    public void setBaiTest(String baiTest) {
        this.baiTest = baiTest;
    }
}