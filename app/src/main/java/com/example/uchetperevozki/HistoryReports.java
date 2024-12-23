package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uchetperevozki.Interface.IReport;
import com.example.uchetperevozki.Interface.IUserCar;
import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.Model.UserCar;
import com.example.uchetperevozki.ModelRequest.ReportResponse;
import com.example.uchetperevozki.RetrofitModels.RetroFit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryReports extends AppCompatActivity {

    private int userId;
    private RecyclerView recyclerView;
    private ReportAdapter reportAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_reports);

        LinearLayout menuContainer = findViewById(R.id.menuContainer);

        // Создаем и используем MenuHandler
        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.setMenuListeners(menuContainer);

        recyclerView = findViewById(R.id.recyclerViewReports);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new NoMarginItemDecoration());
        GetReports();
    }

    public void GetReports(){
        User user = UserSingleton.getInstance().getUser();
        userId = user.id;

        Retrofit retrofit = RetroFit.getClient();
        IReport userCarService = retrofit.create(IReport.class);
        Call<List<ReportResponse>> call = userCarService.getReportsByUser(userId);
        call.enqueue(new Callback<List<ReportResponse>>() {
            @Override
            public void onResponse(Call<List<ReportResponse>> call, Response<List<ReportResponse>> response) {
                if (response.isSuccessful()) {
                    List<ReportResponse> reports = response.body();
                    reportAdapter = new ReportAdapter(reports);
                    recyclerView.setAdapter(reportAdapter);
                    Log.d("Reports", "Received reports: " + response.body().size());
                } else {
                    Log.e("Reports", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ReportResponse>> call, Throwable t) {
                Log.e("Reports", "Failure: " + t.getMessage());
            }
        });

    }


}