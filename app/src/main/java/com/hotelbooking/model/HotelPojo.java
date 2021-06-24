package com.hotelbooking.model;

import com.google.gson.annotations.SerializedName;

public class HotelPojo {

//SELECT `hid`, `hotel_name`, `location`, `rating`, `image` FROM `hotel` WHERE 1

    @SerializedName("hid")
    private String hid;

    @SerializedName("hotel_name")
    private String hotel_name;

    @SerializedName("location")
    private String location;

    @SerializedName("rating")
    private String rating;

    @SerializedName("image")
    private String image;

    @SerializedName("about")
    private String about;


    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
