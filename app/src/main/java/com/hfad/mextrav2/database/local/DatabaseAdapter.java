package com.hfad.mextrav2.database.local;

/**
 * Created by vishwa on 3/7/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vishwa on 3/7/17.
 */

public class DatabaseAdapter {

    //Create a cursor
    Context context;

    try{
        SQLiteOpenHelper mextraDatabaseHelper = new MextraDatabaseHelper(this.context);
        SQLiteDatabase db = mextraDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put()

    }


}
