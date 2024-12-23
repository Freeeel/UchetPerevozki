package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uchetperevozki.Interface.IUser;
import com.example.uchetperevozki.Model.User;
import com.example.uchetperevozki.ModelRequest.UserUpdate;
import com.example.uchetperevozki.RetrofitModels.RetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        EditText phoneText = findViewById(R.id.fillPhone);
        EditText addressResidentialText = findViewById(R.id.fillAddress);
        EditText bankAccountNumberText = findViewById(R.id.fillBankNumber);
        EditText passwordText = findViewById(R.id.fillPassword);
        Button buttonUpdateData = findViewById(R.id.btnUpdateProfile);

        //Валидация
        setupPhoneNumberMask(R.id.fillPhone);
        setupEditTextWithLimit(R.id.fillPassword, 18);
        setupEditTextWithLimit(R.id.fillAddress,50);
        setupEditTextWithLimit(R.id.fillBankNumber,20);

        // Создаем и используем MenuHandler
        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.setMenuListeners(menuContainer);

        User user = UserSingleton.getInstance().getUser();


        int userId = user.id;
        String userName = user.name;
        String userSurname = user.surname;
        String userPatronymic = user.patronymic;
        String userPhone = user.phone;
        String addressResidential = user.address_residential;
        String userPassword = user.password;
        int bankAccountNumber = user.bank_account_number;

        // Отображение данных в активити
        nameText.setText(userName);
        surnameText.setText(userSurname);
        patronymicText.setText(userPatronymic);
        idText.setText(String.valueOf(userId));
        phoneText.setText(userPhone);
        addressResidentialText.setText(addressResidential);
        passwordText.setText(userPassword);
        bankAccountNumberText.setText(String.valueOf(bankAccountNumber));


        buttonUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPhone = phoneText.getText().toString().replaceAll("[^\\d]", "");
                String newAddress = addressResidentialText.getText().toString();
                String newPassword = passwordText.getText().toString();
                Integer newBankAccountNumber;

                String bankAccountStr = bankAccountNumberText.getText().toString();
                if (!bankAccountStr.isEmpty()) {
                    newBankAccountNumber = Integer.valueOf(bankAccountStr);
                } else {
                    newBankAccountNumber = null;
                }

                UserUpdate userUpdate = new UserUpdate();

                // Установка значений только для тех полей, которые были изменены
                if ( !newPassword.equals(userPassword)) {
                    userUpdate.setPassword(newPassword);
                }
                else
                {
                    userUpdate.setPassword(userPassword);
                }

                if (!newPhone.equals(userPhone)) {
                    userUpdate.setPhone(newPhone);
                }
                else
                {
                    userUpdate.setPhone(userPhone);
                }

                if (!newAddress.equals(addressResidential)) {
                    userUpdate.setAddress_residential(newAddress);
                }
                else
                {
                    userUpdate.setAddress_residential(addressResidential);
                }

                if (newBankAccountNumber != null && !newBankAccountNumber.equals(user.bank_account_number)) {
                    userUpdate.setBank_account_number(newBankAccountNumber);
                }
                else
                {
                    userUpdate.setBank_account_number(bankAccountNumber);
                }

                Retrofit retrofit = RetroFit.getClient();
                IUser userService = retrofit.create(IUser.class);
                Call<User> call = userService.updateUser(user.id, userUpdate);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User updatedUser = response.body();
                            Toast.makeText(Profile.this, "Данные успешно обновлены!", Toast.LENGTH_SHORT).show();
                            UserSingleton.getInstance().setUser(updatedUser);
                        } else {
                            Toast.makeText(Profile.this, "Ошибка обновления данных: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(Profile.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void setupEditTextWithLimit(int editTextId, int maxLength) {
        EditText editText = findViewById(editTextId);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    private void setupPhoneNumberMask(int editTextId) {
        EditText phoneNumberEditText = findViewById(editTextId);
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;
            private final String defaultPrefix = "+7 ";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;
                isUpdating = true;

                String phone = s.toString().replaceAll("[^\\d]", "");
                StringBuilder formatted = new StringBuilder();

                if (phone.length() >= 1) formatted.append("+7 ");
                if (phone.length() > 1)
                    formatted.append("(").append(phone.substring(1, Math.min(phone.length(), 4)));
                if (phone.length() >= 4)
                    formatted.append(") ").append(phone.substring(4, Math.min(phone.length(), 7)));
                if (phone.length() >= 7)
                    formatted.append("-").append(phone.substring(7, Math.min(phone.length(), 9)));
                if (phone.length() >= 9)
                    formatted.append("-").append(phone.substring(9, Math.min(phone.length(), 11)));

                phoneNumberEditText.setText(formatted.toString());
                phoneNumberEditText.setSelection(phoneNumberEditText.getText().length());
                isUpdating = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}