package com.hotelbooking.model;

import com.google.gson.annotations.SerializedName;

public class MyProfilePojo {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("pass")
    private String pass;

    public MyProfilePojo(String name, String email, String phone, String pass) {
        this.name=name;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}