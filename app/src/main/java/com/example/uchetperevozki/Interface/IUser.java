package com.example.uchetperevozki.Interface;

import com.example.uchetperevozki.Model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IUser {

    @GET("/user/{login}/{password}")
    Call<User> GetUser(@Path("login") String login, @Path("password") String password);
}
