package com.example.uchetperevozki.Interface;



import com.example.uchetperevozki.Model.Report;

import com.example.uchetperevozki.ModelRequest.ReportCreate;
import com.example.uchetperevozki.ModelRequest.ReportResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IReport {
    @POST("/reports/")
    Call<Report> createReport(@Body ReportCreate report);

    @GET("/reports/user/{user_id}")
    Call<List<ReportResponse>> getReportsByUser(@Path("user_id") int userId);
}
