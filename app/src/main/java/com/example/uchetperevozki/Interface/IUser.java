package com.example.uchetperevozki.Interface;

import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.ModelRequest.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUser {

    @POST("/login")
    Call<User> loginUser(@Body UserLogin userLogin);

}
