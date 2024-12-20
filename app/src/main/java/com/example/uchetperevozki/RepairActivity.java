package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uchetperevozki.Interface.IRepair;
import com.example.uchetperevozki.Interface.IUser;
import com.example.uchetperevozki.Interface.IUserCar;
import com.example.uchetperevozki.Model.Repair;
import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.Model.UserCar;
import com.example.uchetperevozki.ModelRequest.RepairCreate;
import com.example.uchetperevozki.RetrofitModels.RetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepairActivity extends AppCompatActivity {

    private EditText editTextStamp;
    private EditText editTextModel;
    private EditText editTextNumberState;

    private EditText editTextDescription;
    private EditText editTextAddress;
    private EditText editTextDate;
    private int userId;

    private static final String TAG = "RepairsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repair);

        LinearLayout menuContainer = findViewById(R.id.menuContainer);
        editTextStamp = findViewById(R.id.fillBrand);
        editTextModel = findViewById(R.id.fillModel);
        editTextNumberState = findViewById(R.id.fillNumber);
        editTextDescription = findViewById(R.id.fillDescription);
        editTextAddress = findViewById(R.id.fillAddress);
        editTextDate = findViewById(R.id.fillDateTime);
        Button buttonSubmit = findViewById(R.id.btnSaveRepair);

        // Создаем и используем MenuHandler
        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.setMenuListeners(menuContainer);

        User user = UserSingleton.getInstance().getUser();
        if (user != null) {
            userId = user.id;
            Log.d("IdUser", "Выбранный вид: " + userId);
        }

        GetCar(userId);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendRepairData();
            }
        });

    }
    private void SendRepairData() {
        String description = editTextDescription.getText().toString();
        String address = editTextAddress.getText().toString();
        String date = editTextDate.getText().toString();

        RepairCreate repair = new RepairCreate(description, date, address, userId);

        Retrofit retrofit = RetroFit.getClient();
        IRepair repairService = retrofit.create(IRepair.class);
        Call<Repair> call = repairService.createRepair(repair);

        call.enqueue(new Callback<Repair>() {
            @Override
            public void onResponse(Call<Repair> call, Response<Repair> response) {
                if (response.isSuccessful()) {
                    Repair createdRepair = response.body();
                    Toast.makeText(RepairActivity.this, "Ремонт создан ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RepairActivity.this, "Ошибка: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Repair> call, Throwable t) {
                Toast.makeText(RepairActivity.this, "Ошибка соединения: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GetCar(int userId){
        Retrofit retrofit = RetroFit.getClient();
        IUserCar userCarService = retrofit.create(IUserCar.class);
        Call<UserCar> call = userCarService.getUserCar(userId);

        call.enqueue(new Callback<UserCar>() {
            @Override
            public void onResponse(Call<UserCar> call, Response<UserCar> response) {
                if (response.isSuccessful()) {
                    UserCar userCar = response.body();
                    editTextStamp.setText(userCar.getStamp());
                    editTextModel.setText(userCar.getModel());
                    editTextNumberState.setText(userCar.getStateNumber());
                }
                else {
                    Log.e("UserCar", "Запрос не удался или вернул null.");
                }
            }

            @Override
            public void onFailure(Call<UserCar> call, Throwable t) {
                Log.e("UserCar", "Ошибка: " + t.getMessage());
            }
        });
    }
}