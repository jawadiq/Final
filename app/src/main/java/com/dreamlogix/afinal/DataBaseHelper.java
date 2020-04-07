package com.dreamlogix.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jarvis on 10/6/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DBVERSION = "1";
    private static final String DATBASE_NAME = "save.db";
    private static final String TABLE_NAME = "profile";


    public DataBaseHelper(Context context) {
        super(context, DATBASE_NAME, null, 1);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+ "(USER TEXT PRIMARY KEY,PASS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
    public Boolean insertData(String name ,String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues mycontent = new ContentValues();
        mycontent.put("USER", name);
        mycontent.put("PASS", pass);
        long result = db.insert(TABLE_NAME, null, mycontent);
        if (result == -1)
            return false;
        else
            return true;

    }


}
