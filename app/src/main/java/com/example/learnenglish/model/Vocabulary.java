package com.example.learnenglish.model;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    private int idVocabulary;
    private String imageVocabulary;
    private String nameVocabulary;

    public Vocabulary(int idVocabulary, String nameVocabulary, String imageVocabulary) {
        this.idVocabulary = idVocabulary;
        this.imageVocabulary = imageVocabulary;
        this.nameVocabulary = nameVocabulary;
    }

    public Vocabulary() {
    }

    public int getIdVocabulary() {
        return idVocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idVocabulary = idVocabulary;
    }

    public String getImageVocabulary() {
        return imageVocabulary;
    }

    public void setImageVocabulary(String imageVocabulary) {
        this.imageVocabulary = imageVocabulary;
    }

    public String getNameVocabulary() {
        return nameVocabulary;
    }

    public void setNameVocabulary(String nameVocabulary) {
        this.nameVocabulary = nameVocabulary;
    }
}
