package com.hotelbooking.api;




import com.hotelbooking.model.HotelPojo;
import com.hotelbooking.model.MyProfilePojo;
import com.hotelbooking.model.ResponseData;

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
    Call<ResponseData> forgotPassword(@Query("email") String emailid);

    @Multipart
    @POST("hotel/addhotel.php")
    Call<ResponseData> addhotel(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap
    );


}
