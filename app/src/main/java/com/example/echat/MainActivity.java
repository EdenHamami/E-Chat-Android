package com.example.echat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private TextView create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_account=findViewById(R.id.create_account);
        create_account.setOnClickListener(v->{
            Intent i =new Intent(this,RegisterPage.class);
            startActivity(i);
        });


        List<User> users = getUsers();

    }

    private List<User> getUsers() {
        List<User> users= new ArrayList<>();
        //final User[][] users = new User[1][1];
        Call<List<User>> call = RetrofitClient.getInstance().getMyApi().getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> myUserList = response.body();
                //users.set(0, new User[myUserList.size()]);

                for (int i = 0; i < myUserList.size(); i++) {
                    users.add(myUserList.get(i).getUser());
                }

                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
        return users;
    }
}