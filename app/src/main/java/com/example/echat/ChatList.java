package com.example.echat;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatList extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ContactsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").
//                allowMainThreadQueries().build();

       doButton();
        displayList();

    }

    private void displayList() {
        db=AppDB.getInstance(ChatList.this);
        contactDao = db.contactDao();
        contacts = new ArrayList<>();
        RecyclerView listContacts = findViewById(R.id.listContacts);
        adapter=new ContactsListAdapter(ChatList.this);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(ChatList.this));
    }

    public void doButton() {
        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> {
            Intent i = new Intent(ChatList.this, AddContact.class);
            startActivity(i);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapter.setContacts(contacts);
        adapter.notifyDataSetChanged();
    }
}