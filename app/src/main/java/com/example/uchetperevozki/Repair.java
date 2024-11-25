package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Repair extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repair);

        LinearLayout menuContainer = findViewById(R.id.menuContainer);

        // Создаем и используем MenuHandler
        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.setMenuListeners(menuContainer);



    }

}