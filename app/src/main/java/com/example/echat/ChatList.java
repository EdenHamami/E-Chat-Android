package com.example.echat;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        RecyclerView listContacts=findViewById(R.id.listContacts);
        final ContactsListAdapter adapter=new ContactsListAdapter(this);
        listContacts.setAdapter((adapter));
        listContacts.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContact.class);
            startActivity(i);
        });
        List<Contact> contacts=new ArrayList<>();
        contacts.add((new Contact(0,"Eden","0"," ","now")));
        contacts.add((new Contact(1,"Ela","0"," ","now")));
        contacts.add((new Contact(2,"kim","0"," ","now")));
        contacts.add((new Contact(3,"dd","0"," ","now")));
        contacts.add((new Contact(3,"dd","0"," ","now")));
        contacts.add((new Contact(4,"dd","0"," ","now")));
        contacts.add((new Contact(5,"dd","0"," ","now")));
        adapter.setContacts(contacts);

    }
}