package com.example.echat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class RegisterPage extends AppCompatActivity {
    private TextView here;
    private EditText userName, displayName, password, repeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        here = findViewById(R.id.create_account);
        userName = findViewById(R.id.UserName);
        displayName = findViewById(R.id.DisplayName);
        password = findViewById(R.id.Password);
        repeatPassword = findViewById(R.id.RepeatPassword);
        here.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }

    public boolean validateUserName() {
        if (userName.getText().toString().length() < 6) {
            userName.setError("UserName must contain 6 letters");
            return false;
        } else {
            userName.setError(null);
            return true;
        }

    }

    public boolean validateDisplayName() {
        if (displayName.getText().toString().length() < 6) {
            displayName.setError("UserName must contain 6 letters");
            return false;
        } else {
            displayName.setError(null);
            return true;
        }

    }

    public boolean validatePassword() {
        if (password.getText().toString().length() < 6) {
            password.setError("Password Must Be At Least 6 Characters");
            return false;
        } else if (!Pattern.compile("[0-9]").matcher(password.getText().toString()).find()) {
            password.setError("Your Password must contain numbers");
            return false;
        } else if (!Pattern.compile("[A-Z]").matcher(password.getText().toString()).find() &&
                !Pattern.compile("[a-z]").matcher(password.getText().toString()).find()) {
            password.setError("Your Password must contain letters");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }

    public boolean validateRepeatPassword() {
        if (!repeatPassword.getText().toString().equals(password.getText().toString())) {
            repeatPassword.setError("Passwords are not the same");
            return false;
        } else {
            repeatPassword.setError(null);
            return true;
        }

    }

    public void registerUser(View view) {
        if (!validateUserName() || !validateDisplayName() || !validatePassword() || !validateRepeatPassword()) {
            return;
        } else {
            List<Contact> myContacts = new ArrayList<Contact>();
            User user = new User(userName.getText().toString(), displayName.getText().toString(), password.getText().toString(), null, myContacts);

            createUser(user);
            Intent i = new Intent(this, chatPage.class);
            startActivity(i);
        }
    }

    private void createUser(@Body User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api retrofitAPI = retrofit.create(Api.class);

        Call<User> call = retrofitAPI.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(RegisterPage.this, "Data added to API", Toast.LENGTH_SHORT).show();

//                jobEdt.setText("");
//                nameEdt.setText("");
//
//
//                User responseFromAPI = response.body();
//
//                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getJob();
//
//
//                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();

//                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}