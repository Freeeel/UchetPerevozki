package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewReport2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_report2);
    }

    public void backBtnReport2(View view){
        finish();}

    public void nextBtnReport2(View view) {
        Intent intent = new Intent(this, NewReport3.class);
        startActivity(intent);
    }
}