package com.example.uchetperevozki.Model;

public class UserCar {
    private int car_id;
    private String state_number;
    private String model;
    private String stamp;

    public int getCarId() {
        return car_id;
    }

    public void setCarId(int car_id) {
        this.car_id = car_id;
    }

    public String getStateNumber() {
        return state_number;
    }

    public void setStateNumber(String state_number) {
        this.state_number = state_number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
