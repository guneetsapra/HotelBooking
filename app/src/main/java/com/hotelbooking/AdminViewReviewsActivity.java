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
import com.hotelbooking.adapters.AdminHotelswithReviews;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.HotelPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminViewReviewsActivity extends AppCompatActivity {

    List<HotelPojo> hotelInfo;
    ListView list_view;
    ProgressDialog loading;
    Button btnAddHotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_reviews);
        getSupportActionBar().setTitle("Hotels List");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view = (ListView) findViewById(R.id.hotel_list);

        hotelInfo = new ArrayList<>();
        getHotels();

    }

    public void getHotels() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}