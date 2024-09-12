package com.example.learnenglish.model;

import java.io.Serializable;

public class VocabularyItem implements Serializable {
    private int idItem;
    private int idVocabulary;
    private int level;
    private String englishWordItem;
    private String vietnameseWordItem;
    private String imageItem;
    private String soundItem;




    public VocabularyItem(int idItem, int idVocabulary, int level,String englishWordItem, String vietnameseWordItem, String imageItem, String soundItem) {
        this.idItem = idItem;
        this.idVocabulary = idVocabulary;
        this.level = level;
        this.englishWordItem = englishWordItem;
        this.vietnameseWordItem = vietnameseWordItem;
        this.imageItem = imageItem;
        this.soundItem = soundItem;

    }

    public VocabularyItem() {
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdVocabulary() {
        return idVocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idVocabulary = idVocabulary;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEnglishWordItem() {
        return englishWordItem;
    }

    public void setEnglishWordItem(String englishWordItem) {
        this.englishWordItem = englishWordItem;
    }

    public String getVietnameseWordItem() {
        return vietnameseWordItem;
    }

    public void setVietnameseWordItem(String vietnameseWordItem) {
        this.vietnameseWordItem = vietnameseWordItem;
    }

    public String getImageItem() {
        return imageItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }

    public String getSoundItem() {
        return soundItem;
    }

    public void setSoundItem(String soundItem) {
        this.soundItem = soundItem;
    }
}
