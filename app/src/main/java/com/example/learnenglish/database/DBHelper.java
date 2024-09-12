package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    public final static String DBNAME= "tienganh123.sqlite";
    public final static String DBLOCATION="/data/data/com.example.learnenglish/databases/";
    final Context mContext;
    private SQLiteDatabase mDatabase;

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    public void openDatabase() {
        String dbPath = DBLOCATION + DBNAME;
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBNAME);
            String outFileName = DBLOCATION + DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getDataFromSQLite(String sql){
        return  mDatabase.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
