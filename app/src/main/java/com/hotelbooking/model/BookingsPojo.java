package com.hotelbooking.model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

public class BookingsPojo {

    @SerializedName("bid")
    private String bid;

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


    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private String age;

    @SerializedName("bdate")
    private String bdate;

    @SerializedName("room")
    private String room;

    @SerializedName("days")
    private String days;


    @SerializedName("payment")
    private String payment;

    @SerializedName("cardname")
    private String cardname;

    @SerializedName("cardno")
    private String cardno;

    @SerializedName("cardexp")
    private String cardexp;

    @SerializedName("cardcvv")
    private String cardcvv;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private String status;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardexp() {
        return cardexp;
    }

    public void setCardexp(String cardexp) {
        this.cardexp = cardexp;
    }

    public String getCardcvv() {
        return cardcvv;
    }

    public void setCardcvv(String cardcvv) {
        this.cardcvv = cardcvv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
