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
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ChatList.this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            PutTokenParam putTokenParam = new PutTokenParam(userName, newToken);
            putToken(putTokenParam);
        });


    }

    private void putToken(PutTokenParam newToken) {
        Call<Void> call = RetrofitClient.getInstance().getMyApi().PutToken(newToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Toast.makeText(ChatList.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), "An error has occured in putToken", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displayList() {
        contacts = new ArrayList<>();
        //contacts = getContacts();
        db=AppDB.getInstance(ChatList.this);
        contactDao = db.contactDao();


//        List<Contact> deleteContactsFromDao = new ArrayList<>();
//        deleteContactsFromDao.addAll(contactDao.index());
//        for(int i = 0; i < deleteContactsFromDao.size(); i++) {
//            contactDao.delete(deleteContactsFromDao.get(i).getContact());
//        }
//
//        for(int i = 0; i < contacts.size(); i++) {
//            contactDao.insert(contacts.get(i).getContact());
//        }

        List<Contact> allContacts = new ArrayList<>();
        allContacts = getContacts();
        allContacts.addAll(contactDao.index());

//        for(int i = 0; i < allContacts.size() ;i++) {
//            if(allContacts.get(i).getUserName().equals(userName)) {
//                contacts.add(allContacts.get(i));
//            }
//        }


        for(int i = 0; i < allContacts.size() ;i++) {
            if(Objects.equals(allContacts.get(i).getUserName(), userName)) {
//                if(Objects.equals(allContacts.get(i+1).getId(), allContacts.get(i).getId())) {
//                    i++;
//                }
                contacts.add(allContacts.get(i));
            }
        }


//            messages.add(newMessage);
//        adapter.setMessages(messages);
//        adapter.notifyDataSetChanged();
//        messageRV.setAdapter(adapter);



        setOnClickListener();
        RecyclerView listContacts = findViewById(R.id.listContacts);
        adapter=new ContactsListAdapter(ChatList.this,listener);
        adapter.setContacts(contacts);
        adapter.notifyDataSetChanged();
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(ChatList.this));
    }

    private void setOnClickListener() {
//        List<Contact> allContacts = new ArrayList<>();
//        allContacts = getContacts();
//        allContacts.addAll(contactDao.index());
//
//        for(int i = 0; i < allContacts.size() ;i++) {
//            if(allContacts.get(i).getUserName() == userName) {
//                contacts.add(allContacts.get(i));
//            }
//        }
        listener=new ContactsListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getApplicationContext(),chatPage.class);
                intent.putExtra("Username",userName);
                intent.putExtra("ContactUsername",contacts.get(position).getId());
                intent.putExtra("ContactName",contacts.get(position).getName());
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
//        contacts.addAll(contactDao.index());

        List<Contact> allContacts = new ArrayList<>();
        allContacts = getContacts();
        allContacts.addAll(contactDao.index());

        for(int i = 0; i < allContacts.size() ;i++) {
            if(Objects.equals(allContacts.get(i).getUserName(), userName)) {
//                if(Objects.equals(allContacts.get(i+1).getId(), allContacts.get(i).getId())) {
//                    i++;
//                }
                contacts.add(allContacts.get(i));
            }
        }


        adapter.setContacts(contacts);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapter.setContacts(contacts);
        adapter.notifyDataSetChanged();
    }



    private List<Contact> getContacts() {
        List<Contact> contacts= new ArrayList<>();
        Call<List<GetContactsParam>> call = RetrofitClient.getInstance().getMyApi().getContacts(userName);
        call.enqueue(new Callback<List<GetContactsParam>>() {
            @Override
            public void onResponse(Call<List<GetContactsParam>> call, Response<List<GetContactsParam>> response) {
                contacts.clear();
                List<GetContactsParam> myContactList = response.body();
                //users.set(0, new User[myUserList.size()]);
                if(myContactList != null) {
                    for (int i = 0; i < myContactList.size(); i++) {

                        Contact newContact = new Contact(myContactList.get(i).getId(), myContactList.get(i).getName(),
                                myContactList.get(i).getServer(), myContactList.get(i).getLast(), myContactList.get(i).getLastDate(), userName);
                        contacts.add(newContact);

                        if(myContactList.size() > (i+1)) {
                            if (Objects.equals(myContactList.get(i + 1).getId(), myContactList.get(i).getId())) {
                                i++;
                            }
                        }


                    }
                }
//                for(int i = 0; i < contacts.size(); i++) {
//                    contactDao.insert(contacts.get(i).getContact());
//                }
                adapter.setContacts(contacts);
                adapter.notifyDataSetChanged();


                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users));
            }

            @Override
            public void onFailure(Call<List<GetContactsParam>> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), "An error has occured in getContacts", Toast.LENGTH_LONG).show();
            }

        });
        return contacts;
    }



}