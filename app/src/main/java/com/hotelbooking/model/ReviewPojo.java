package com.hotelbooking.model;

import com.google.gson.annotations.SerializedName;

public class ReviewPojo {

    @SerializedName("hid")
    private String hid;

    @SerializedName("name")
    private String name;

    @SerializedName("rating")
    private String rating;

    @SerializedName("email")
    private String email;

    @SerializedName("msg")
    private String msg;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
