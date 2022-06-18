package com.example.echat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chatPage extends AppCompatActivity {
    private TextView contactName;

    private MessageDB db;
    private MessageDao messageDao;
    private List<Message> messages;
    private String userName;
    private String contactUserName;
    private String contactNameString;
    private RecyclerView messageRV;
    private MessagesListAdapter adapter;



    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        contactName = findViewById(R.id.contactName);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                userName = null;
            } else {
                userName = extras.getString("Username");
            }
        } else {
            userName = (String) savedInstanceState.getSerializable("Username");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                contactUserName = null;
            } else {
                contactUserName = extras.getString("ContactUsername");
            }
        } else {
            contactUserName = (String) savedInstanceState.getSerializable("ContactUsername");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                contactNameString = null;
            } else {
                contactNameString = extras.getString("ContactName");
            }
        } else {
            contactNameString = (String) savedInstanceState.getSerializable("ContactName");
        }
        contactName.setText(contactNameString);

        List<Message> allMessages;

        messages = new ArrayList<>();
//        messages.clear();

        allMessages = getMessages();

        db = MessageDB.getInstance(chatPage.this);

        messageDao = db.messageDao();

//        List<Message> deleteContactsFromDao = new ArrayList<>();
//        deleteContactsFromDao.addAll(messageDao.index());
//        for(int i = 0; i < deleteContactsFromDao.size(); i++) {
//            messageDao.delete(deleteContactsFromDao.get(i).getMessage());
//        }
//        for(int i = 0; i < messages.size(); i++) {
//            messageDao.insert(messages.get(i).getMessage());
//        }

        allMessages.addAll(messageDao.index());

        List<Message> messagesOfUserAndContact = new ArrayList<>();

        for(int i = 0; i < allMessages.size() ;i++) {
            if((allMessages.get(i).getMessage().getFrom().equals(userName)) && (allMessages.get(i).getMessage().getTo().equals(contactUserName))) {
                messages.add(allMessages.get(i).getMessage());
            }
            if((allMessages.get(i).getMessage().getFrom().equals(contactUserName)) && (allMessages.get(i).getMessage().getTo().equals(userName))) {
                messages.add(allMessages.get(i).getMessage());
            }
        }

        adapter = new MessagesListAdapter(this, messages, userName);
        messageRV = findViewById(R.id.recycler_view);
        messageRV.setLayoutManager(new LinearLayoutManager(this));
        messageRV.setAdapter(adapter);



        FloatingActionButton sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(view -> {

            EditText content = findViewById(R.id.writeMessage);

            String contentString = content.getText().toString();
            Date currentTime = Calendar.getInstance().getTime();

            Message newMessage = new Message(contentString, currentTime.toString(), contactUserName, userName);
            messageDao.insert(newMessage);
            onResume();


            CreateMessageParam messageParam = new CreateMessageParam(contentString);

            createMessage(messageParam);

            TransferParam transferParam = new TransferParam(userName, contactUserName, contentString);
            NewMessage(transferParam);
//
//            finish();
        });


    }

    private List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        Call<List<GetMessagesParam>> call = RetrofitClient.getInstance().getMyApi().getMessages(userName, contactUserName);
        call.enqueue(new Callback<List<GetMessagesParam>>() {
            @Override
            public void onResponse(Call<List<GetMessagesParam>> call, Response<List<GetMessagesParam>> response) {
                List<GetMessagesParam> myMessageList = response.body();
                if (myMessageList != null) {
                    for (int i = 0; i < myMessageList.size(); i++) {
                        if (myMessageList.get(i).getSent() == true) {
                            Message newMessage = new Message(myMessageList.get(i).getContent(), myMessageList.get(i).getCreated(), contactUserName, userName);
                            messages.add(newMessage);
                        } else {
                            Message newMessage = new Message(myMessageList.get(i).getContent(), myMessageList.get(i).getCreated(), userName, contactUserName);
                            messages.add(newMessage);
                        }
                    }
                }
                adapter.setMessages(messages);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<GetMessagesParam>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured in getMessages", Toast.LENGTH_LONG).show();
            }

        });
        return messages;
    }

    private void createMessage(CreateMessageParam message) {
        Call<Void> call = RetrofitClient.getInstance().getMyApi().createMessage(userName, contactUserName, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(chatPage.this, "Data added to API", Toast.LENGTH_SHORT).show();

//                adapter.setMessages(messages);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured in createMessage", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void NewMessage(TransferParam newMessage) {
        Call<Void> call = RetrofitClient.getInstance().getMyApi().NewMessage(newMessage);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(chatPage.this, "Data added to API", Toast.LENGTH_SHORT).show();
//                adapter.setMessages(messages);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured in NewMessage", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        messages.clear();

        List<Message> allMessages;
        allMessages = getMessages();

        allMessages.addAll(messageDao.index());

//        for(int i = 0; i < allMessages.size() ;i++) {
//            if((allMessages.get(i).getFrom() == userName) && (allMessages.get(i).getTo() == contactUserName)) {
//                messages.add(allMessages.get(i));
//            }
//            if((allMessages.get(i).getFrom() == contactUserName) && (allMessages.get(i).getTo() == userName)) {
//                messages.add(allMessages.get(i));
//            }
//        }



//        List<Message> messagesOfUserAndContact = new ArrayList<>();
//
//        for(int i = 0; i < messages.size() ;i++) {
//            if(messages.get(i).getFrom() == userName && messages.get(i).getTo() == contactUserName) {
//                messagesOfUserAndContact.add(messages.get(i));
//            }
//            if(messages.get(i).getFrom() == contactUserName && messages.get(i).getTo() == userName) {
//                messagesOfUserAndContact.add(messages.get(i));
//            }
//        }

        adapter.setMessages(messages);
        adapter.notifyDataSetChanged();
        messageRV.setAdapter(adapter);


    }

}
