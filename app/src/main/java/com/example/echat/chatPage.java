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
    private Contact currentContact;
    private MessagesListAdapter adapter;
    private TextView tvContact;

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

        messages = getMessages();

        adapter = new MessagesListAdapter(this, messages, userName);
        messageRV = findViewById(R.id.recycler_view);
        messageRV.setLayoutManager(new LinearLayoutManager(this));
        messageRV.setAdapter(adapter);

        db = MessageDB.getInstance(chatPage.this);

        messageDao = db.messageDao();

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

            }

            @Override
            public void onFailure(Call<List<GetMessagesParam>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
        return messages;
    }

    private void createMessage(CreateMessageParam message) {
        Call<CreateMessageParam> call = RetrofitClient.getInstance().getMyApi().createMessage(userName, contactUserName, message);
        call.enqueue(new Callback<CreateMessageParam>() {
            @Override
            public void onResponse(Call<CreateMessageParam> call, Response<CreateMessageParam> response) {
                Toast.makeText(chatPage.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CreateMessageParam> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void NewMessage(TransferParam newMessage) {
        Call<TransferParam> call = RetrofitClient.getInstance().getMyApi().NewMessage(newMessage);
        call.enqueue(new Callback<TransferParam>() {
            @Override
            public void onResponse(Call<TransferParam> call, Response<TransferParam> response) {
                Toast.makeText(chatPage.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TransferParam> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        messages.clear();
        messages.addAll(messageDao.index());

        adapter.setMessages(messages);
        adapter.notifyDataSetChanged();
        messageRV.setAdapter(adapter);
     //   setContentView(R.layout.activity_chat_page);
//        binding.recyclerView1.setVisibility(View.VISIBLE);


    }
}
//import android.annotation.SuppressLint;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.view.inputmethod.InputMethodManager;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.TextView;
//
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.recyclerview.widget.DividerItemDecoration;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import com.example.myapplication.databinding.ActivityChatBinding;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//public class ChatActivity extends AppCompatActivity {
//
//    private AppDb db;
//    private MessagesDao messagesDao;
//    private ContactDao contactDao;
//    private List<Message> messages = new ArrayList<>();
//    private MessagesAdapter adapter;
//    private RecyclerView rcMessages;
//    private TextView tvContact;
//    private ActivityChatBinding binding;
//    private Contact currentContact;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_chat);
//        binding = ActivityChatBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        tvContact = findViewById(R.id.selected_contact);
//        Intent intent = getIntent();
//
//        if (intent.getExtras() != null) {
//            Contact contact = (Contact) intent.getSerializableExtra("data");
//            tvContact.setText(contact.getId());
//            currentContact = contact;
//        }
//        db = AppDb.getDb(this);
////        db = Room.databaseBuilder(getApplicationContext(), AppDb.class, "messagesDB")
////                .allowMainThreadQueries().build();
//        messagesDao = db.messagesDao();
//        contactDao = db.contactDao();
//        messages = messagesDao.get(currentContact.getId());
//
//        adapter = new MessagesAdapter(this, messages);
//        rcMessages = findViewById(R.id.recycler_view1);
//        rcMessages.setLayoutManager(new LinearLayoutManager(this));
//        rcMessages.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        rcMessages.setAdapter(adapter);
//
//        Button btnSaveNewMessage = findViewById(R.id.btnSendMessage);
//        btnSaveNewMessage.setOnClickListener(v -> {
//            EditText newMessage = findViewById(R.id.message_box);
//            String content = newMessage.getText().toString();
//            Message message = new Message( content, currentContact.getId());
//            messagesDao.insert(message);
//            binding.messageBox.setText("");
//            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
//            onResume();
//            currentContact.setLast(content);
//            currentContact.setLastDate(message.getCreated());
//            contactDao.update(currentContact);
//        });
//
//    }
//    @SuppressLint("NotifyDataSetChanged")
//    @Override
//    protected void onResume(){
//        super.onResume();
//        messages.clear();
//        messages.addAll(messagesDao.get(currentContact.getId()));
//        adapter.notifyDataSetChanged();
//        rcMessages.setAdapter(adapter);
//        //setContentView(R.layout.activity_chat);
//        binding.recyclerView1.setVisibility(View.VISIBLE);
//    }
//
//}