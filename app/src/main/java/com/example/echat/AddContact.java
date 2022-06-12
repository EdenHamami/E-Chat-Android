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
            EditText etIten=findViewById(R.id.etItem);
            EditText etIten=findViewById(R.id.etItem);
            EditText etIten=findViewById(R.id.etItem);

            String newContactName=etIten.getText().toString();
            String newContactUserName=etIten.getText().toString();
            String newContactServer=etIten.getText().toString();

//            List<Message> messages=new ArrayList<Message>() {};
            Contact contact=new Contact(newContactUserName,newContactName,"",null,null);
            contactDao.insert(contact);
            finish();
        });
    }
}