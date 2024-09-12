package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;

import com.example.learnenglish.model.Question;

import java.util.ArrayList;

public class TestItemDatabase extends DBHelper {
    private Context context;

    public TestItemDatabase(Context context) {
        super(context);
    }

    public ArrayList<Question> getListQuestion(int baiTest) {
        Question question=null;
        ArrayList<Question> itemList = new ArrayList<>();
        TestItemDatabase database = new TestItemDatabase(context);
        database.openDatabase();
        Cursor cursor = database.getDataFromSQLite("SELECT * FROM Test WHERE baiTest = '"+baiTest+"' ORDER BY RANDOM() LIMIT 10");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            question= new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),"");
            itemList.add(question);
            cursor.moveToNext();
        }
        cursor.close();
        database.closeDatabase();
        return itemList;
    }
}