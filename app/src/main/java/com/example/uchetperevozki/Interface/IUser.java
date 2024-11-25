package com.example.uchetperevozki.Interface;

import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.ModelRequest.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUser {

//    @GET("/user/{login}/{password}")
//    Call<User>GetUser(@Path("login") String login, @Path("password") String password);

    @POST("/login")
    Call<User> loginUser(@Body UserLogin userLogin);

}
