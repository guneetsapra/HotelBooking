package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hotelbooking.adapters.AdminHotelListAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.HotelPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminHomeActivity extends AppCompatActivity {

    List<HotelPojo> hotelInfo;
    ListView list_view;
    ProgressDialog progressDialog;
    Button btnAddHotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().setTitle("Hotels List");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddHotel = (Button) findViewById(R.id.add_hotel);
        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AddHotelActivity.class);
                startActivity(intent);
            }
        });

        list_view = (ListView) findViewById(R.id.hotel_list);

        hotelInfo = new ArrayList<>();
        getHotels();

    }
}