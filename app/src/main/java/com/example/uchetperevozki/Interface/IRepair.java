package com.example.uchetperevozki.Interface;

import com.example.uchetperevozki.Model.Repair;
import com.example.uchetperevozki.ModelRequest.RepairCreate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRepair {
    @POST("/repairs/")
    Call<Repair> createRepair(@Body RepairCreate repair);
}
