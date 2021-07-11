package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.hotelbooking.adapters.AdminHotelListAdapter;
import com.hotelbooking.adapters.RoomsAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.HotelPojo;
import com.hotelbooking.model.RoomsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRoomsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RoomsAdapter AdapterUser;
    ArrayList<RoomsPojo> list;

    List<RoomsPojo> hotelInfo;
    ListView list_view;
    ProgressDialog loading;
    Button btnAddHotel;
    GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms);

        getSupportActionBar().setTitle("Rooms");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridview = (GridView) findViewById(R.id.gridview);
        hotelInfo = new ArrayList<>();
        getRooms();

    }

    public void getRooms() {
        loading = new ProgressDialog(ViewRoomsActivity.this);
        loading.setMessage("Loading....");
        loading.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<RoomsPojo>> call = service.getRooms(getIntent().getStringExtra("hid"));
        call.enqueue(new Callback<List<RoomsPojo>>() {
            @Override
            public void onResponse(Call<List<RoomsPojo>> call, Response<List<RoomsPojo>> response) {
                loading.dismiss();
                //   Toast.makeText(AdminHotelsActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(ViewRoomsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    gridview.setAdapter(new RoomsAdapter(hotelInfo, ViewRoomsActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<RoomsPojo>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(ViewRoomsActivity.this, "Something went wrong...Please contact admin !", Toast.LENGTH_SHORT).show();
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