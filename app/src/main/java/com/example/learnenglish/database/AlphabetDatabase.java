package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;
import com.example.learnenglish.model.Alphabet;
import java.util.ArrayList;
import java.util.List;

public class AlphabetDatabase extends DBHelper{
    private Context context;

    public AlphabetDatabase(Context context) {
        super(context);
    }

    public List<Alphabet> getListAlphabet() {
        Alphabet alphabet = null;
        List<Alphabet> alphabetList = new ArrayList<>();
        AlphabetDatabase dbHelper = new AlphabetDatabase(context);
        dbHelper.openDatabase();
        Cursor cursor = dbHelper.getDataFromSQLite("SELECT * FROM Alphabet ");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            alphabet = new Alphabet(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
            alphabetList.add(alphabet);
            cursor.moveToNext();
        }
        cursor.close();
        dbHelper.closeDatabase();
        return (ArrayList<Alphabet>) alphabetList;
    }
}
