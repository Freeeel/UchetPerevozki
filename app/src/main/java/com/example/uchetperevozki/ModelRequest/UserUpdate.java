package com.example.uchetperevozki.ModelRequest;

public class UserUpdate {
    private String password;
    private String phone;
    private String address_residential;
    private int bank_account_number;

    // Constructors
    public UserUpdate() {}

    public UserUpdate(String password, String phone, String address_residential, int bank_account_number) {
        this.password = password;
        this.phone = phone;
        this.address_residential = address_residential;
        this.bank_account_number = bank_account_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress_residential(String address_residential) {
        this.address_residential = address_residential;
    }

    public void setBank_account_number(int bank_account_number) {
        this.bank_account_number = bank_account_number;
    }
}
