package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;

import com.example.learnenglish.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabularyDatabase extends DBHelper{
    private Context context;

    public VocabularyDatabase(Context context) {
        super(context);
    }


    public ArrayList<Vocabulary> getListVocabulary() {
        Vocabulary vocabulary = null;
        List<Vocabulary> vocabularyArrayList = new ArrayList<>();
        VocabularyDatabase db = new VocabularyDatabase(context);
        db.openDatabase();
        Cursor cursor = db.getDataFromSQLite("SELECT * FROM Chude");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            vocabularyArrayList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDatabase();
        return (ArrayList<Vocabulary>) vocabularyArrayList;
    }
    public Vocabulary getListVocabularyFromId(int id) {
        Vocabulary vocabulary = null;
        //List<Vocabulary> vocabularyList = new ArrayList<>();
        VocabularyDatabase db = new VocabularyDatabase(context);
        db.openDatabase();
        Cursor cursor = db.getDataFromSQLite("SELECT * FROM Chude WHERE Id = '"+id+"'");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            //vocabularyList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDatabase();
        return vocabulary;
    }
}
