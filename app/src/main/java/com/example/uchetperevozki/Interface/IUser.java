package com.example.uchetperevozki.Interface;

import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.ModelRequest.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUser {

    @POST("/login")
    Call<User> loginUser(@Body UserLogin userLogin);

    @GET("/users/{user_id}")
    Call<User> getUserById(@Path("user_id") int userId);

}
