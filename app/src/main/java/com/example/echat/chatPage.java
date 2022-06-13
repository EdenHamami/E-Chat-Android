package com.example.echat;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        contactName=findViewById(R.id.contactName);

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
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                contactUserName= null;
            } else {
                contactUserName= extras.getString("ContactUsername");
            }
        } else {
            contactUserName= (String) savedInstanceState.getSerializable("ContactUsername");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                contactNameString= null;
            } else {
                contactNameString= extras.getString("ContactName");
            }
        } else {
            contactNameString= (String) savedInstanceState.getSerializable("ContactName");
        }
        contactName.setText(contactNameString);

        messages = getMessages();
    }

    private List<Message> getMessages() {
        List<Message> messages= new ArrayList<>();
        Call<List<GetMessagesParam>> call = RetrofitClient.getInstance().getMyApi().getMessages(userName, contactUserName);
        call.enqueue(new Callback<List<GetMessagesParam>>() {
            @Override
            public void onResponse(Call<List<GetMessagesParam>> call, Response<List<GetMessagesParam>> response) {
                List<GetMessagesParam> myMessageList = response.body();
                if(myMessageList != null) {
                    for (int i = 0; i < myMessageList.size(); i++) {
                        if(myMessageList.get(i).getSent() == true) {
                            Message newMessage = new Message(myMessageList.get(i).getContent(), myMessageList.get(i).getCreated(), contactUserName, userName);
                            messages.add(newMessage);
                        }
                        else {
                            Message newMessage = new Message(myMessageList.get(i).getContent(), myMessageList.get(i).getCreated(), userName, contactUserName);
                            messages.add(newMessage);
                        }

                    }
                }
//                for(int i = 0; i < messages.size(); i++) {
//                    MessageDao.insert(messages.get(i).getContact());
//                }
//                adapter.setContacts(messages);
//                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<GetMessagesParam>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
        return messages;
    }
}