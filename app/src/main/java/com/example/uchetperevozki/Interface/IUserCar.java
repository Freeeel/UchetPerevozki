package com.example.uchetperevozki.Interface;

import com.example.uchetperevozki.Model.UserCar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IUserCar {
    @GET("user/{user_id}/car")
    Call<UserCar> getUserCar(@Path("user_id") int userId);
}
