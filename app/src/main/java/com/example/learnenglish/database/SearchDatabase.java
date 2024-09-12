package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.learnenglish.model.VocabularyItem;

import java.util.ArrayList;
import java.util.List;

public class SearchDatabase extends DBHelper{
    private Context context;

    public SearchDatabase(Context context) {
        super(context);
    }


            public List<VocabularyItem> getVocabularyItemByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id","Tutienganh","Tutiengviet","Anh","Sound"};
        String tableName = "Itemchude";
        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,"Tutienganh LIKE ?",new String[]{"%"+name+"%"},
                null,null,null);
        List<VocabularyItem> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                VocabularyItem vocabularyItem = new VocabularyItem();
                vocabularyItem.setIdItem(cursor.getInt(cursor.getColumnIndex("Id")));
                vocabularyItem.setEnglishWordItem(cursor.getString(cursor.getColumnIndex("Tutienganh")));
                vocabularyItem.setVietnameseWordItem(cursor.getString(cursor.getColumnIndex("Tutiengviet")));
                vocabularyItem.setImageItem(cursor.getString(cursor.getColumnIndex("Anh")));
                vocabularyItem.setSoundItem(cursor.getString(cursor.getColumnIndex("Sound")));
                result.add(vocabularyItem);
            }while (cursor.moveToNext());
        }
        return result;
    }

}

