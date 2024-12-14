package com.example.uchetperevozki.Interface;



import com.example.uchetperevozki.Model.Report;

import com.example.uchetperevozki.ModelRequest.ReportCreate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IReport {
    @POST("/reports/")
    Call<Report> createReport(@Body ReportCreate report);
}
