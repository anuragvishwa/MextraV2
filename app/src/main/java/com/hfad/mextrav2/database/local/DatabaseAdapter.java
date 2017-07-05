package com.hfad.mextrav2.database.local;

/**
 * Created by vishwa on 3/7/17.
 */

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.hfad.mextrav2.model.Contact;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by vishwa on 3/7/17.
 */

public class DatabaseAdapter {
    Context context;
    Cursor cursor = null;
    ContentResolver contentResolver;
    Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    String phoneNumber = null;
    StringBuffer output;
    String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    SQLiteDatabase db;


    public void StoreContacts(Context context) {

      /*  SQLiteOpenHelper mextraDatabaseChecker = new MextraDatabaseHelper(context);
        db = mextraDatabaseChecker.getReadableDatabase();
        final Cursor cursorcheck = db.rawQuery("SELECT COUNT(_id) FROM CONTACTS",null);
        if(cursorcheck==null) {
*/

            contentResolver = context.getContentResolver();

            try {

                cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            } catch (Exception ex) {
                Log.e("Error fetching contact", ex.getMessage());
            }


            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    String contact_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    int contact_id_int = Integer.parseInt(contact_id);

                    String display_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    int hasMobNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                    if (hasMobNumber > 0) {
                        Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                        while (phoneCursor.moveToNext()) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));


                            try {

                                SQLiteOpenHelper mextraDatabaseHelper = new MextraDatabaseHelper(context);
                                db = mextraDatabaseHelper.getWritableDatabase();
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("cpi", contact_id_int);
                                contentValues.put("name", display_name);
                                contentValues.put("mobile", phoneNumber);
                                contentValues.put("datetime", timeStamp);
                                db.insert("CONTACTS", null, contentValues);
                                // Log.v("DatabaseAdapter",phoneNumber);

                            } catch (SQLiteException ex) {
                                Log.v("DatabaseAdapter", ex.getMessage());
                            }


                        }

                        phoneCursor.close();

                    }

                }
            }
            cursor.close();
            db.close();

            //Create a cursor


      //  }

    }
}
