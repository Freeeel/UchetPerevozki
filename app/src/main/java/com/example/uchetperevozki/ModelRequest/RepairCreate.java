package com.example.uchetperevozki.ModelRequest;

public class RepairCreate {
    private String description_breakdown;
    private String date_and_time_repair;
    private String address_point_repair;
    private int user_id;

    // Конструктор
    public RepairCreate(String description_breakdown,String date_and_time_repair, String address_point_repair,  int user_id) {
        this.description_breakdown = description_breakdown;
        this.date_and_time_repair = date_and_time_repair;
        this.address_point_repair = address_point_repair;
        this.user_id = user_id;
    }
}
