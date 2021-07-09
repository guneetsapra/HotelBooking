package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.hotelbooking.adapters.AdminBookingsAdapter;
import com.hotelbooking.adapters.MyBookingsAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.BookingsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminViewBookingsActivity extends AppCompatActivity {

    List<BookingsPojo> hotelInfo;
    ListView list_bookings;
    ProgressDialog loading;
    SharedPreferences sharedPreferences;
    AdminBookingsAdapter myBookingsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_bookings);
        getSupportActionBar().setTitle("Customer Bookings List");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_bookings = (ListView)findViewById(R.id.list_bookings);


        hotelInfo = new ArrayList<>();
        getMyBookings();
    }

    public void getMyBookings() {
//        loading = new ProgressDialog(getApplicationContext());
//        loading.setMessage("Customer Bookings Loading");
//        loading.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<BookingsPojo>> call = service.myadminbookings();
        call.enqueue(new Callback<List<BookingsPojo>>() {
            @Override
            public void onResponse(Call<List<BookingsPojo>> call, Response<List<BookingsPojo>> response) {
                //   loading.dismiss();
                if (response.body() == null) {
                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    myBookingsAdapter=new AdminBookingsAdapter(hotelInfo,getApplicationContext());
                    list_bookings.setAdapter(myBookingsAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<BookingsPojo>> call, Throwable t) {
                //  loading.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
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