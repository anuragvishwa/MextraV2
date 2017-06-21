package com.hfad.mextrav2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.hfad.mextrav2.adapter.ContactsAdapter;
import com.hfad.mextrav2.model.Contact;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Contact> contactList;
    ContactsAdapter.FetchContactsAll fetchContactsAll = new ContactsAdapter.FetchContactsAll();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView = (RecyclerView)findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactList = fetchContactsAll.contacts;
        adapter = new ContactsAdapter(contactList,this);
        recyclerView.setAdapter(adapter);


    }

    
}
