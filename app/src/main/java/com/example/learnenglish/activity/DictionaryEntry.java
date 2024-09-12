package com.example.learnenglish.activity;

public class DictionaryEntry {
    private String partOfSpeech;
    private String definition;
    private String example;

    public DictionaryEntry(String partOfSpeech, String definition, String example) {
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.example = example;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExample() {
        return example;
    }

    @Override
    public String toString() {
        return partOfSpeech + " " + definition + "\nExample: " + example;
    }
}
