package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewReport3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_report3);

    }

    public void backBtnReport3(View view){
        finish();}

    public void nextBtnReport3(View view) {
        Intent intent = new Intent(this, HistoryReports.class);
        startActivity(intent);
    }
}