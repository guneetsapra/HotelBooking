package com.hotelbooking.api;




import com.hotelbooking.model.BookingsPojo;
import com.hotelbooking.model.HotelPojo;
import com.hotelbooking.model.MyProfilePojo;
import com.hotelbooking.model.ResponseData;
import com.hotelbooking.model.ReviewPojo;
import com.hotelbooking.model.RoomsPojo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/hotel/user_registration.php?")
    Call<ResponseData> user_registration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("pass") String pass,
            @Query("phone") String phone);

    @GET("/hotel/user_login.php?")
    Call<ResponseData> user_login(
            @Query("email") String email,
            @Query("pass") String pass);


    @GET("/hotel/admin.php?")
    Call<ResponseData> adminlogin(
            @Query("email") String email,
            @Query("password") String password);

    @GET("/hotel/myprofile.php?")
    Call<List<MyProfilePojo>> getProfile(
            @Query("email") String email);

    @GET("/hotel/updateprofile.php?")
    Call<ResponseData> update_profile(
            @Query("name") String name,
            @Query("email") String email,
            @Query("pass") String pass,
            @Query("phone") String phone);

    @GET("hotel/gethotels.php?")
    Call<List<HotelPojo>> gethotel();

    @GET("hotel/forgotPass.php")
    Call<ResponseData> getPassword(@Query("emailid") String emailid);

    @Multipart
    @POST("hotel/addhotel.php")
    Call<ResponseData> addhotel(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap
    );


    @Multipart
    @POST("hotel/addroom.php")
    Call<ResponseData> addroom(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap
    );

    @GET("/hotel/removehotel.php")
    Call<ResponseData> deletehotel(@Query("id") String id);



    @GET("/hotel/updatehotel.php?")
    Call<ResponseData> updatehotel(
            @Query("hname") String hname,
            @Query("location") String location,
            @Query("about") String about,
            @Query("rating") String rating,
            @Query("hid") String hid);


    //name,age,bdate,room,days,payment,cardname,cardno,cardexp,cardcvv,email,hid
    @GET("/hotel/bookHotel.php?")
    Call<ResponseData> book(
            @Query("name") String name,
            @Query("age") String age,
            @Query("bdate") String bdate,
            @Query("room") String room,
            @Query("days") String days,
            @Query("payment") String payment,
            @Query("cardname") String cardname,
            @Query("cardno") String cardno,
            @Query("cardexp") String cardexp,
            @Query("cardcvv") String cardcvv,
            @Query("email") String email,
            @Query("hid") String hid);

    //name,msg,email,rating,hid
    @GET("/hotel/writeReview.php?")
    Call<ResponseData> writeReview(
            @Query("name") String name,
            @Query("msg") String msg,
            @Query("email") String email,
            @Query("rating") String rating,
            @Query("hid") String hid);

    @GET("/hotel/getreviews.php?")
    Call<List<ReviewPojo>> getreviews(
            @Query("hid") String hid);


    @GET("/hotel/getRooms.php?")
    Call<List<RoomsPojo>> getRooms(
            @Query("hid") String hid);

    @GET("/hotel/cancelBooking.php?")
    Call<ResponseData> cancelBooking(
            @Query("bid") String bid,
            @Query("email") String email);


    @GET("/hotel/mybookings.php?")
    Call<List<BookingsPojo>> mybookings(
            @Query("email") String email);

    @GET("/hotel/myadminbookings.php?")
    Call<List<BookingsPojo>> myadminbookings();

}
