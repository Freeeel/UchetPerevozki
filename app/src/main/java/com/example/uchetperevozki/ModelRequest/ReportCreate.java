package com.example.uchetperevozki.ModelRequest;

public class ReportCreate {
    private String point_departure;
    private String type_point_departure;
    private String sender;
    private String point_destination;
    private String type_point_destination;
    private String recipient;
    private String view_wood;
    private int length_wood;
    private float volume_wood;
    private String report_date_time;
    private String assortment_wood_type;
    private String variety_wood_type;
    private int user_id;

    public ReportCreate(String point_departure, String type_point_departure,
                        String sender, String point_destination, String type_point_destination,
                        String recipient, String view_wood, int length_wood, float volume_wood,
                        String report_date_time, String assortment_wood_type, String variety_wood_type,
                        int user_id){

        this.point_departure=point_departure;
        this.type_point_departure=type_point_departure;
        this.sender=sender;
        this.point_destination=point_destination;
        this.type_point_destination=type_point_destination;
        this.recipient=recipient;
        this.view_wood=view_wood;
        this.length_wood=length_wood;
        this.volume_wood=volume_wood;
        this.report_date_time=report_date_time;
        this.assortment_wood_type=assortment_wood_type;
        this.variety_wood_type=variety_wood_type;
        this.user_id=user_id;
    }
}
