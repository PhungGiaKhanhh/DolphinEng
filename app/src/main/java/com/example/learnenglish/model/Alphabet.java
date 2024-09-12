package com.example.learnenglish.model;

import java.io.Serializable;

public class Alphabet implements Serializable {
    private int idAlphabet;
    private String nameAlphabet;
    private String soundAlphabet;
    private String spellAlphabet;

    public Alphabet(int idAlphabet, String nameAlphabet, String soundAlphabet, String spellAlphabet) {
        this.idAlphabet = idAlphabet;
        this.nameAlphabet = nameAlphabet;
        this.soundAlphabet = soundAlphabet;
        this.spellAlphabet = spellAlphabet;
    }

    public int getIdAlphabet() {
        return idAlphabet;
    }

    public void setIdAlphabet(int idAlphabet) {
        this.idAlphabet = idAlphabet;
    }

    public String getNameAlphabet() {
        return nameAlphabet;
    }

    public void setNameAlphabet(String nameAlphabet) {
        this.nameAlphabet = nameAlphabet;
    }

    public String getSoundAlphabet() {
        return soundAlphabet;
    }

    public void setSoundAlphabet(String soundAlphabet) {
        this.soundAlphabet = soundAlphabet;
    }

    public String getSpellAlphabet() {
        return spellAlphabet;
    }

    public void setSpellAlphabet(String spellAlphabet) {
        this.spellAlphabet = spellAlphabet;
    }
}
