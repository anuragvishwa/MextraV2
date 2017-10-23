package com.hfad.mextrav2.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.mextrav2.R;
import com.hfad.mextrav2.database.local.MextraDatabaseHelper;
import com.hfad.mextrav2.model.Contact;

import java.util.ArrayList;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

   // public HashMap<Integer,Contact> contactList;
    public ArrayList<Contact> allContacts;

    public static ArrayList<Contact> contactList = new ArrayList<Contact>();


    Context context;

    public ContactsAdapter(ArrayList<Contact> listContacts,Context context)
    {
        //readContacts();
        allContacts = listContacts;
        this.context = context;
    }

    public static ArrayList<Contact> readContacts(Context context)
    {
        Contact contact ;
        SQLiteOpenHelper mextraDatabasehelper = new MextraDatabaseHelper(context);
        SQLiteDatabase db = mextraDatabasehelper.getReadableDatabase();
        Cursor cursor = db.query("CONTACTS",
                new String[] {"name", "mobile"},
                null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                contact = new Contact();
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String mobileno = cursor.getString(cursor.getColumnIndex("mobile"));

                contact.setName(name);
                contact.setMob(mobileno);
                contactList.add(contact);

            }while(cursor.moveToNext());
        }
        cursor.close();

        for(int i=0;i<contactList.size();i++)
        {
            Contact c= contactList.get(i);
            Log.v("ContactList",c.getMob());
        }
        return contactList;

    }

   /* public class FetchContactsAll {

        public ArrayList<Integer> ListNumbers = new ArrayList<Integer>();

        //Reading from the content provider and populating the Arraylist:

        Context context;
        Cursor cursor = null;
        ContentResolver contentResolver;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String phoneNumber = null;
        StringBuffer output;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        public HashMap<Integer,Contact> FetchContactsAll(Context context)
        {
            contentResolver = context.getContentResolver();
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
                    contact.setName(display_name);
                    int hasMobNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                    if(hasMobNumber>0)
                    {
                        Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
                        while (phoneCursor.moveToNext()) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                            int mobile = Integer.parseInt( phoneNumber);
                            ListNumbers.add(mobile);
                        }
                        phoneCursor.close();
                        contact.setMob(ListNumbers);
                    }
                    contactList.put(Integer.parseInt(contact_id),contact);
                }
            }
            return contactList;

        }

    }*/

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

        }
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contacts,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        Contact ListItem = allContacts.get(position);
        holder.title.setText(ListItem.getName());
        holder.status.setText(ListItem.getMob());
        holder.main_image.setImageResource(R.drawable.logo);
        holder.timestamp.setText("5:55 am");
        holder.notification.setText("23");
        holder.silentstatus.setText("2");
    }

    @Override
    public int getItemCount() {
        return allContacts.size() ;
    }
}
