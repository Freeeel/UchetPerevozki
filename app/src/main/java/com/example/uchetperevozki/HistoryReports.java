package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HistoryReports extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_reports);

        LinearLayout menuContainer = findViewById(R.id.menuContainer);

        // Создаем и используем MenuHandler
        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.setMenuListeners(menuContainer);
    }


}