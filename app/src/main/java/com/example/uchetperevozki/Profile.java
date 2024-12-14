package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        LinearLayout menuContainer = findViewById(R.id.menuContainer);

        TextView nameText = findViewById(R.id.nameText);
        TextView surnameText = findViewById(R.id.surnameText);
        TextView patronymicText = findViewById(R.id.patronymicText);
        TextView idText = findViewById(R.id.idText);
        TextView dateBirthdayText = findViewById(R.id.dateBirthdayText);
        EditText phoneText = findViewById(R.id.fillPhone);
        EditText addressResidentialText = findViewById(R.id.fillAddress);
        EditText bankAccountNumberText = findViewById(R.id.fillBankNumber);
        EditText passwordText = findViewById(R.id.fillPassword);


        // Создаем и используем MenuHandler
        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.setMenuListeners(menuContainer);

        // Извлечение данных
        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", -1);  // Извлечение userId
        String userName = intent.getStringExtra("user_name");
        String userSurname = intent.getStringExtra("user_surname");
        String userPatronymic = intent.getStringExtra("user_patronymic");
        String userPhone = intent.getStringExtra("user_phone");
        String dateBirthday =  intent.getStringExtra("user_date_birthday");
        String addressResidential = intent.getStringExtra("user_address_residential");
        String userPassword = intent.getStringExtra("user_password");
        int bankAccountNumber = intent.getIntExtra("user_bank_account_number", -1);
        intent.putExtra("idUser", userId);
        // Отображение данных в активити
        nameText.setText(userName);
        surnameText.setText(userSurname);
        patronymicText.setText(userPatronymic);
        idText.setText(String.valueOf(userId));
        phoneText.setText(userPhone);
        dateBirthdayText.setText(dateBirthday);
        addressResidentialText.setText(addressResidential);
        passwordText.setText(userPassword);
        bankAccountNumberText.setText(String.valueOf(bankAccountNumber));
        Toast.makeText(Profile.this," "+ dateBirthday ,Toast.LENGTH_SHORT).show();



    }


}