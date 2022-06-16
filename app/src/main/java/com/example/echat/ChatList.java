package com.example.echat;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatList extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ContactsListAdapter adapter;
    private String userName;
    private ContactsListAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
            } else {
                userName= extras.getString("Username");
            }
        } else {
            userName= (String) savedInstanceState.getSerializable("Username");
        }

        doButton();
        displayList();

    }

    private void displayList() {
        contacts = new ArrayList<>();
        contacts = getContacts();
        db=AppDB.getInstance(ChatList.this);
        contactDao = db.contactDao();


        List<Contact> deleteContactsFromDao = new ArrayList<>();
        deleteContactsFromDao.addAll(contactDao.index());
        for(int i = 0; i < deleteContactsFromDao.size(); i++) {
            contactDao.delete(deleteContactsFromDao.get(i).getContact());
        }

        for(int i = 0; i < contacts.size(); i++) {
            contactDao.insert(contacts.get(i).getContact());
        }
        setOnClickListener();
        RecyclerView listContacts = findViewById(R.id.listContacts);
        adapter=new ContactsListAdapter(ChatList.this,listener);
        adapter.setContacts(contacts);
        adapter.notifyDataSetChanged();
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(ChatList.this));
    }

    private void setOnClickListener() {
        listener=new ContactsListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getApplicationContext(),chatPage.class);
                intent.putExtra("Username",contacts.get(position).getId());
                intent.putExtra("Name",contacts.get(position).getName());
                startActivity(intent);
            }
        };
    }

    public void doButton() {
        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> {
            Intent i = new Intent(ChatList.this, AddContact.class);
            i.putExtra("Username",userName);
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

    private List<Contact> getContacts() {
        List<Contact> contacts= new ArrayList<>();
        Call<List<Contact>> call = RetrofitClient.getInstance().getMyApi().getContacts(userName);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> myContactList = response.body();
                //users.set(0, new User[myUserList.size()]);
                if(myContactList != null) {
                    for (int i = 0; i < myContactList.size(); i++) {
                        contacts.add(myContactList.get(i).getContact());
                    }
                }
                for(int i = 0; i < contacts.size(); i++) {
                    contactDao.insert(contacts.get(i).getContact());
                }
                adapter.setContacts(contacts);
                adapter.notifyDataSetChanged();


                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users));
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
        return contacts;
    }
//    protected void onDestroy() {
//        List<Contact> contacts = new ArrayList<>();
//        contacts.addAll(contactDao.index());
//        for(int i = 0; i < contacts.size(); i++) {
//            contactDao.delete(contacts.get(i).getContact());
//        }
//
//        super.onDestroy();
//    }

}