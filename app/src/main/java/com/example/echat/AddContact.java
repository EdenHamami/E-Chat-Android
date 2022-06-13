package com.example.echat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private String userName;


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


        db=AppDB.getInstance(AddContact.this);

        contactDao = db.contactDao();
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener( view -> {

            EditText contactsName=findViewById(R.id.contactName);
            EditText contactsUserName=findViewById(R.id.contactsUsername);
            EditText contactServer=findViewById(R.id.addServer);
            String newContactName=contactsName.getText().toString();
            String newContactUsername=contactsUserName.getText().toString();
            String newContactSever=contactServer.getText().toString();
//            List<Message> messages=new ArrayList<Message>() {};
            Contact contact=new Contact(newContactUsername,newContactName,newContactSever,null,null);
            contactDao.insert(contact);

            CreateContactParam contactParam = new CreateContactParam(newContactUsername, newContactName, newContactSever);

            createContact(contactParam);

            InvitationsParam newConversation = new InvitationsParam(newContactUsername, userName, newContactSever);
            NewConversation(newConversation);

            finish();
        });
    }

    private void createContact(CreateContactParam contact) {
        Call<CreateContactParam> call = RetrofitClient.getInstance().getMyApi().createContact(userName, contact);
        call.enqueue(new Callback<CreateContactParam>() {
            @Override
            public void onResponse(Call<CreateContactParam> call, Response<CreateContactParam> response) {
                Toast.makeText(AddContact.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<CreateContactParam> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void NewConversation(InvitationsParam newConversation) {
        Call<InvitationsParam> call = RetrofitClient.getInstance().getMyApi().NewConversation(newConversation);
        call.enqueue(new Callback<InvitationsParam>() {
            @Override
            public void onResponse(Call<InvitationsParam> call, Response<InvitationsParam> response) {
                Toast.makeText(AddContact.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<InvitationsParam> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}