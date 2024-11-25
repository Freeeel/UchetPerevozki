package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uchetperevozki.Interface.IUser;
import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.ModelRequest.UserLogin;
import com.example.uchetperevozki.RetrofitModels.RetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Аuthorization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editTextLogin = findViewById(R.id.editTextLogin);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonSignIn = findViewById(R.id.btnSignIn);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editTextLogin.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Аuthorization.this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Создание UserLogin
                UserLogin userLogin = new UserLogin(login, password);

                // Создание RetroFit
                Retrofit retrofit = RetroFit.getClient();
                IUser userService = retrofit.create(IUser.class);
                Call<User> call = userService.loginUser(userLogin);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();

                            Toast.makeText(Аuthorization.this, "Добро пожаловать: " + user.name, Toast.LENGTH_SHORT).show();

                            //Переход на другую активность
                            Intent intent = new Intent(Аuthorization.this, Profile.class);

                            //Передача данных
                            intent.putExtra("user_id", user.id);
                            intent.putExtra("user_name", user.name);
                            intent.putExtra("user_surname", user.surname);
                            intent.putExtra("user_patronymic", user.patronymic);
                            intent.putExtra("user_phone", user.phone);
                            intent.putExtra("user_password", user.password);
                            intent.putExtra("user_address_residential", user.address_residential);
                            intent.putExtra("user_bank_account_number", user.bank_account_number);
                            intent.putExtra("user_date_birthday", user.date_birthday);



                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Аuthorization.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                            Log.e("Authorization", "Invalid credentials response: " + response.code() + ", message: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("Authorization", "Network error: " + t.getMessage());
                        Toast.makeText(Аuthorization.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

//    public void main(View view){
//        Intent intent = new Intent(this, HistoryReports.class);
//        startActivity(intent);}


