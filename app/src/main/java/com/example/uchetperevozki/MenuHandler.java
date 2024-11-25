package com.example.uchetperevozki;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;

public class MenuHandler {

    private Context context;

    public MenuHandler(Context context) {
        this.context = context;
    }

    public void setMenuListeners(LinearLayout menuContainer) {
        LinearLayout menuHistory = menuContainer.findViewById(R.id.menuHistoryReports);
        LinearLayout menuReports = menuContainer.findViewById(R.id.menuReport);
        LinearLayout menuRepair = menuContainer.findViewById(R.id.menuRepair);
        LinearLayout menuProfile = menuContainer.findViewById(R.id.menuProfile);

        menuHistory.setOnClickListener(v -> {
            Intent intent = new Intent(context, HistoryReports.class);
            context.startActivity(intent);
        });

        menuReports.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewReport1.class);
            context.startActivity(intent);
        });

        menuRepair.setOnClickListener(v -> {
            Intent intent = new Intent(context, Repair.class);
            context.startActivity(intent);
        });

        menuProfile.setOnClickListener(v -> {
            Intent intent = new Intent(context, Profile.class);
            context.startActivity(intent);
        });
    }
}