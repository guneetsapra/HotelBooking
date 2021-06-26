package com.hotelbooking.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String URL = "http://cegephotel.com/";
    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
