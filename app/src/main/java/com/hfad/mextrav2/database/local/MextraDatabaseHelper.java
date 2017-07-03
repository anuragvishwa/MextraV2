package com.hfad.mextrav2.database.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MextraDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mextra";
    private static final int DB_VERSION =1;

    MextraDatabaseHelper(Context context){

        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE CONTACTS(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cpi INTEGER," +
                "name TEXT," +
                "mobile INTEGER," +
                "datetime NUMERIC );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion)
    {
        
    }




}
