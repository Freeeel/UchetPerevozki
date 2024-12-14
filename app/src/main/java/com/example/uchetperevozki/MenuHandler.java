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
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        });

        menuReports.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewReport1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        });

        menuRepair.setOnClickListener(v -> {
            Intent intent = new Intent(context, RepairActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        });

        menuProfile.setOnClickListener(v -> {
            Intent intent = new Intent(context, Profile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        });
    }
}