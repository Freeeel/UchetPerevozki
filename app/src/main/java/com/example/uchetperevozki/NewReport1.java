package com.example.uchetperevozki;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewReport1 extends AppCompatActivity {

    private EditText editTextDeparturePoint;
    private EditText editTextTypeDeparturePoint;
    private EditText editTextSender;
    private EditText editTextTargetPoint;
    private EditText editTextTypeTargetPoint;
    private EditText editTextRecipient;
    private Button buttonNext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_report1);

        editTextDeparturePoint = findViewById(R.id.fillDeparturePoint);
        editTextTypeDeparturePoint = findViewById(R.id.fillTypeDeparturePoint);
        editTextSender = findViewById(R.id.fillSender);
        editTextTargetPoint = findViewById(R.id.fillTargetPoint);
        editTextTypeTargetPoint = findViewById(R.id.fillTypeTargetPoint);
        editTextRecipient = findViewById(R.id.fillRecipient);
        buttonNext1 = findViewById(R.id.btnNextReport1);

        setupEditTextWithLimit(R.id.fillDeparturePoint,50);
        setupEditTextWithLimit(R.id.fillTypeDeparturePoint,50);
        setupEditTextWithLimit(R.id.fillSender,100);
        setupEditTextWithLimit(R.id.fillTargetPoint,50);
        setupEditTextWithLimit(R.id.fillTypeTargetPoint,50);
        setupEditTextWithLimit(R.id.fillRecipient,50);

        buttonNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivityReport1();
            }
        });
    }
    public void backBtnReport1(View view){
        finish();}

    private void nextActivityReport1(){

        String departurePoint = editTextDeparturePoint.getText().toString();
        String typeDeparturePoint = editTextTypeDeparturePoint.getText().toString();
        String sender = editTextSender.getText().toString();
        String targetPoint = editTextTargetPoint.getText().toString();
        String typeTargetPoint = editTextTypeTargetPoint.getText().toString();
        String recipient = editTextRecipient.getText().toString();

        if (departurePoint.isEmpty() || typeDeparturePoint.isEmpty() || sender.isEmpty() ||
                targetPoint.isEmpty() || typeTargetPoint.isEmpty() || recipient.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все обязательные поля.", Toast.LENGTH_SHORT).show();
            return;  // Завершить метод, если есть незаполненные поля
        }

        Intent intent = new Intent(NewReport1.this, NewReport2.class);
        intent.putExtra("departure_point", departurePoint);
        intent.putExtra("type_departure_point", typeDeparturePoint);
        intent.putExtra("sender", sender);
        intent.putExtra("target_point", targetPoint);
        intent.putExtra("type_target_point", typeTargetPoint);
        intent.putExtra("recipient", recipient);

        startActivity(intent);
    }
    private void setupEditTextWithLimit(int editTextId, int maxLength) {
        EditText editText = findViewById(editTextId);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }
}