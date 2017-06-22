package com.hfad.mextrav2.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.mextrav2.R;
import com.hfad.mextrav2.model.Contact;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by energywin4 on 16/6/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public ArrayList<Contact> contactList;
    Context context;

    public ContactsAdapter(ArrayList<Contact> listContacts,Context context)
    {
        contactList = listContacts;
        this.context = context;
    }

    public static class FetchContactsAll {

        public ArrayList<Contact> contacts = new ArrayList<Contact>();

        //Reading from the content provider and populating the Arraylist:

        Context context;
        Cursor cursor = null;
        ContentResolver contentResolver;

        public ArrayList<Contact> FetchContactsAll(Context context)
        {
            contentResolver = context.getContentResolver();

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




    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView main_image;
        public TextView title;
        public TextView status;
        public TextView timestamp;
        public TextView notification;
        public TextView silentstatus;
        public View stline;


        public ViewHolder(View itemView) {
            super(itemView);

            main_image = (ImageView) itemView.findViewById(R.id.main_image);
            title = (TextView) itemView.findViewById(R.id.title);
            status = (TextView) itemView.findViewById(R.id.status);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            notification = (TextView) itemView.findViewById(R.id.notification);
            silentstatus = (TextView) itemView.findViewById(R.id.silentstatus);
            stline = (View) itemView.findViewById(R.id.stline);
        }
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_frag,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        FetchContactsAll fetchContactsAll = new FetchContactsAll();
        Contact ListItem = fetchContactsAll.contacts.get(position);

        holder.title.setText(ListItem.getName());
        holder.status.setText(ListItem.getMob());
        holder.main_image.setImageResource(R.drawable.ic_add);
        holder.timestamp.setText("5:55 am");
        holder.notification.setText("23");
        holder.silentstatus.setText("2");
        holder.stline.bringToFront();

    }

    @Override
    public int getItemCount() {
        return new FetchContactsAll().contacts.size() ;
    }
}
