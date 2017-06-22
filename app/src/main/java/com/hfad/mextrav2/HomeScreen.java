package com.hfad.mextrav2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.hfad.mextrav2.adapter.ContactsAdapter;
import com.hfad.mextrav2.model.Contact;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Contact> contactList;
    ContactsAdapter.FetchContactsAll fetchContactsAll = new ContactsAdapter.FetchContactsAll();
    public ArrayList<Contact> allContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, 1);



    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    recyclerView = (RecyclerView)findViewById(R.id.recylerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                  //  contactList = fetchContactsAll.contacts;
                    allContacts=fetchContactsAll.FetchContactsAll(this);
                    adapter = new ContactsAdapter(allContacts,this);
                    recyclerView.setAdapter(adapter);

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
