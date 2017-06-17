package com.hfad.mextrav2.adapter;

import android.content.ContentResolver;
import android.database.Cursor;

import com.hfad.mextrav2.model.Contact;

import java.util.ArrayList;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;


/**
 * Created by energywin4 on 17/6/2017.
 */

public class FetchContacts {

    public ArrayList<Contact> contacts = new ArrayList<Contact>();

    //Reading from the content provider and populating the Arraylist:

    Context context;
    Cursor cursor = null;
    ContentResolver contentResolver = context.getContentResolver();

    public ArrayList<Contact> FetchContacts()
    {

        //this.context = context;

        try {

            cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

        }

        catch (Exception ex) {

            Log.e("Error fetching contact",ex.getMessage());

        }


      if(cursor.getCount()>0){
          while(cursor.moveToNext())
          {

              Contact contact = new Contact();

              String contact_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
              String display_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


              //Setting the name to contact Model:

              contact.setName(display_name);

              //--- Check if mobile number is present

              int hasMobNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

              if(hasMobNumber>0)
              {
                 Cursor phoneCursor = contentResolver.query(
                          ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                          ,null
                          ,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?"
                          ,new String[]{contact_id}
                          ,null);

                  while(phoneCursor.moveToNext()) {


                      int phoneNumber = Integer.parseInt(phoneCursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                      //Set the data to contacts :
                      contact.setMob(phoneNumber);

                  }

                  phoneCursor.close();

                  contacts.add(contact);

              }



          }








      }

  return contacts;
    }

}
