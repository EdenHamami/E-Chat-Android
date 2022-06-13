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
    Call<CreateUserParam> createUser(@Body CreateUserParam user);

    @GET("{UserName}")
    Call<List<Contact>> getContacts(@Path("UserName") String UserName);

    @POST("{UserName}")
    Call<CreateContactParam> createContact(@Path("UserName") String UserName, @Body CreateContactParam contact);

    @POST("{UserName}/{id}/messages")
    Call<Message> createMessage(@Path("UserName") String UserName, @Path("id") String id, @Body Message message);
}