package com.hotelbooking.model;

import com.google.gson.annotations.SerializedName;

public class RoomsPojo {

    @SerializedName("hid")
    private String hid;

    @SerializedName("hrid")
    private String hrid;

    @SerializedName("type")
    private String type;

    @SerializedName("price")
    private String price;

    @SerializedName("image")
    private String image;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHrid() {
        return hrid;
    }

    public void setHrid(String hrid) {
        this.hrid = hrid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
