package com.example.uchetperevozki.ModelRequest;

import com.google.gson.annotations.SerializedName;

public class ReportResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("point_departure")
    private String pointDeparture;

    @SerializedName("type_point_departure")
    private String typePointDeparture;

    @SerializedName("sender")
    private String sender;

    @SerializedName("point_destination")
    private String pointDestination;

    @SerializedName("type_point_destination")
    private String typePointDestination;

    @SerializedName("recipient")
    private String recipient;

    @SerializedName("view_wood")
    private String viewWood;

    @SerializedName("length_wood")
    private int lengthWood;

    @SerializedName("volume_wood")
    private float volumeWood;

    @SerializedName("report_date_time")
    private String reportDateTime;

    @SerializedName("assortment_wood_type")
    private String assortmentWoodType;

    @SerializedName("variety_wood_type")
    private String varietyWoodType;

    @SerializedName("user_id")
    private int userId;

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getPointDeparture() {
        return pointDeparture;
    }

    public String getTypePointDeparture() {
        return typePointDeparture;
    }

    public String getSender() {
        return sender;
    }

    public String getPointDestination() {
        return pointDestination;
    }

    public String getTypePointDestination() {
        return typePointDestination;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getViewWood() {
        return viewWood;
    }

    public int getLengthWood() {
        return lengthWood;
    }

    public float getVolumeWood() {
        return volumeWood;
    }

    public String getReportDateTime() {
        return reportDateTime;
    }

    public String getAssortmentWoodType() {
        return assortmentWoodType;
    }

    public String getVarietyWoodType() {
        return varietyWoodType;
    }

    public int getUserId() {
        return userId;
    }
}