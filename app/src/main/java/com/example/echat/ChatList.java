package com.example.echat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatList extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").
                allowMainThreadQueries().build();
        contactDao = db.contactDao();
        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContact.class);
            startActivity(i);
        });
        contacts = new ArrayList<>();
        ListView lvContacts = findViewById(R.id.lvContacts);
        adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        lvContacts.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
       contacts.clear();
       contacts.addAll(contactDao.index());
       adapter.notifyDataSetChanged();
    }
}