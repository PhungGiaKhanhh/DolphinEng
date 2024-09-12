package com.example.learnenglish.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "Signup.db";
    public DatabaseHelper(Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDataBase) {
        MyDataBase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDataBase, int oldVersion, int newVersion) {
        MyDataBase.execSQL("drop Table if exists allusers ");

    }
    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result==-1){
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MySatabase = this.getWritableDatabase();
        Cursor cursor = MySatabase.rawQuery ("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        } else{
            return false;}
    }
    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MySatabase = this.getWritableDatabase();
        Cursor cursor = MySatabase.rawQuery ("Select * from allusers where email = ? and password = ?", new String[]{email, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
