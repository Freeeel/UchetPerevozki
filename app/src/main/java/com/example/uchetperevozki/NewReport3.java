package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uchetperevozki.Interface.IRepair;
import com.example.uchetperevozki.Interface.IReport;
import com.example.uchetperevozki.Model.Repair;
import com.example.uchetperevozki.Model.Report;
import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.ModelRequest.RepairCreate;
import com.example.uchetperevozki.ModelRequest.ReportCreate;
import com.example.uchetperevozki.RetrofitModels.RetroFit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewReport3 extends AppCompatActivity {

    private EditText editTextDeparturePoint;
    private EditText editTextSender;
    private EditText editTextTargetPoint;
    private EditText editTextRecipient;
    private EditText editTextViewWood;
    private EditText editTextLongWood;
    private EditText editTextVolumeWood;
    private EditText editTextAssortmentWood;
    private EditText editTextVarietyWood;

    private String departurePoint;
    private String typeDeparturePoint;
    private String sender;
    private String targetPoint;
    private String typeTargetPoint;
    private String recipient;
    private String viewWood;
    private int longWood;
    private String varietyWood;
    private String assortmentWood;
    private float volumeWood;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_report3);

        editTextDeparturePoint = findViewById(R.id.fillDeparturePointFinal);
        editTextSender = findViewById(R.id.fillSenderFinal);
        editTextTargetPoint = findViewById(R.id.fillTargetPointFinal);
        editTextRecipient = findViewById(R.id.fillRecipientFinal);
        editTextViewWood = findViewById(R.id.fillViewWoodFinal);
        editTextLongWood= findViewById(R.id.fillLongWoodFinal);
        editTextVolumeWood = findViewById(R.id.fillVolumeWoodFinal);
        editTextAssortmentWood = findViewById(R.id.fillAssortmentWoodFinal);
        editTextVarietyWood = findViewById(R.id.fillVarietyWoodFinal);
        DataDisplay();

        User user = UserSingleton.getInstance().getUser();
        if (user != null) {
            userId = user.id;
            Log.d("IdUser", "Выбранный вид: " + userId);
        }

    }

    public void backBtnReport3(View view){
        finish();}

    public void nextBtnReport3(View view) {
        SendReportData();
        Intent intent = new Intent(this, HistoryReports.class);
        startActivity(intent);
    }

    public void DataDisplay(){

        Intent intent = getIntent();

        // Получаем данные из Intent
        departurePoint = intent.getStringExtra("departure_point");
        typeDeparturePoint = intent.getStringExtra("type_departure_point");
        sender = intent.getStringExtra("sender");
        targetPoint = intent.getStringExtra("target_point");
        typeTargetPoint = intent.getStringExtra("type_target_point");
        recipient = intent.getStringExtra("recipient");
        viewWood = intent.getStringExtra("view_wood");
        longWood = intent.getIntExtra("long_wood", 0);
        varietyWood = intent.getStringExtra("variety_wood");
        assortmentWood = intent.getStringExtra("assortment_wood");
        volumeWood = intent.getFloatExtra("volume_wood", 0);

        editTextDeparturePoint.setText(departurePoint);
        editTextSender.setText(sender);
        editTextTargetPoint.setText(targetPoint);
        editTextRecipient.setText(recipient);
        editTextViewWood.setText(viewWood);
        editTextLongWood.setText(String.valueOf(longWood));
        editTextVarietyWood.setText(varietyWood);
        editTextAssortmentWood.setText(assortmentWood);
        editTextVolumeWood.setText(String.valueOf(volumeWood));
    }

    public void SendReportData(){

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String reportDate = currentDate.format(formatter);

        ReportCreate report = new ReportCreate(departurePoint, typeDeparturePoint, sender, targetPoint,
             typeDeparturePoint, recipient, viewWood, longWood, volumeWood, reportDate, assortmentWood, varietyWood, userId);

        Log.d("ReportCreate", "Departure Point: " + departurePoint);
        Log.d("ReportCreate", "Type Departure Point: " + typeDeparturePoint);
        Log.d("ReportCreate", "Sender: " + sender);
        Log.d("ReportCreate", "Target Point: " + targetPoint);
        Log.d("ReportCreate", "Recipient: " + recipient);
        Log.d("ReportCreate", "View Wood: " + viewWood);
        Log.d("ReportCreate", "Long Wood: " + longWood);
        Log.d("ReportCreate", "Volume Wood: " + volumeWood);
        Log.d("ReportCreate", "Report Date: " + reportDate);
        Log.d("ReportCreate", "Assortment Wood: " + assortmentWood);
        Log.d("ReportCreate", "Variety Wood: " + varietyWood);
        Log.d("ReportCreate", "User ID: " + userId);

        Retrofit retrofit = RetroFit.getClient();
        IReport reportService = retrofit.create(IReport.class);
        Call<Report> call = reportService.createReport(report);

        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if(response.isSuccessful()){
                    Report createdReport = response.body();
                    Toast.makeText(NewReport3.this, "Отчёт создан: ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewReport3.this, "Ошибка: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                Toast.makeText(NewReport3.this, "Ошибка соединения: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}