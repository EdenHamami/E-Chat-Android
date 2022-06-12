package com.example.echat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://10.0.2.2:7213/api/contacts/";
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<List<String>> createUser(@Body List<String> user);

    @POST("{UserName}")
    Call<Contact> createContact(@Path("UserName") String UserName, @Body Contact contact);

    @POST("{UserName}/{id}/messages")
    Call<Message> createMessage(@Path("UserName") String UserName, @Path("id") String id, @Body Message message);
}