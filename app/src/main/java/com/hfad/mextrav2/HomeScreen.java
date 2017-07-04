package com.hfad.mextrav2;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


//import com.hfad.mextrav2.adapter.ContactsAdapter;
import com.hfad.mextrav2.adapter.ContactsAdapter;
import com.hfad.mextrav2.database.local.DatabaseAdapter;
import com.hfad.mextrav2.model.Contact;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Contact> contactList;
   // ContactsAdapter.FetchContactsAll fetchContactsAll;
    public ArrayList<Contact> allContacts;
    public static final int REQUEST_READ_CONTACTS = 79;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        askForContactPermission();

        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, 1);

    }


    public void fetchTheContacts()
    {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        databaseAdapter.StoreContacts(this);
    }




    public void getContact(){

        fetchTheContacts();

        recyclerView = (RecyclerView)findViewById(R.id.recylerView);
      //  recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //  contactList = fetchContactsAll.contacts;
/*
        fetchContactsAll = new ContactsAdapter.FetchContactsAll();



        allContacts=fetchContactsAll.FetchContactsAll(this);
*/
        allContacts = ContactsAdapter.readContacts(this);

        adapter = new ContactsAdapter(allContacts,this);

        recyclerView.setAdapter(adapter);
    }


    public void askForContactPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
               /* if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("please confirm Contacts access");//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , REQUEST_READ_CONTACTS);
                        }
                    });
                    builder.show();*/
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

               /* } else {*/

                    // No explanation needed, we can request the permission.



                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            REQUEST_READ_CONTACTS);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
               /* }*/
            }else{

                getContact();
            }
        }
        else{
            getContact();
        }
    }



    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getContact();

                } else {

                    Toast.makeText(
                            getApplicationContext(),
                            "You didn't grant permission",
                            Toast.LENGTH_LONG).show();

                }
                return;
            }

        }
    }






    
}
