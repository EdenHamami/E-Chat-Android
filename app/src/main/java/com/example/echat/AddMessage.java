package com.example.echat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;


import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessage extends AppCompatActivity {
    private MessageDB db;
    private MessageDao messageDao;
    private String userName;
    private String contactUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

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
                contactUserName= extras.getString("contactUserName");
            }
        } else {
            contactUserName= (String) savedInstanceState.getSerializable("contactUserName");
        }


        db=MessageDB.getInstance(AddMessage.this);

        messageDao = db.messageDao();
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener( view -> {

            EditText content=findViewById(R.id.contactName);

            String contentString=content.getText().toString();
            Date currentTime = Calendar.getInstance().getTime();


            Message newMessage=new Message(contentString, currentTime.toString(), contactUserName,userName);
            messageDao.insert(newMessage);

            CreateMessageParam messageParam = new CreateMessageParam(contentString);

            createMessage(messageParam);

            finish();
        });
    }

    private void createMessage(CreateMessageParam message) {
        Call<CreateMessageParam> call = RetrofitClient.getInstance().getMyApi().createMessage(userName, contactUserName, message);
        call.enqueue(new Callback<CreateMessageParam>() {
            @Override
            public void onResponse(Call<CreateMessageParam> call, Response<CreateMessageParam> response) {
                Toast.makeText(AddMessage.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<CreateMessageParam> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}