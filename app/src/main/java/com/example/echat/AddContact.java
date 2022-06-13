package com.example.echat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

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
            Contact contact=new Contact(newContactUserName,newContactName,"",null,null);
            contactDao.insert(contact);
            finish();
        });
    }
}