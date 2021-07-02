package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditHotelActivity extends AppCompatActivity {

    EditText etHotelName,etLocation,etAbout;
    Button btn_upload,btnUpdateHotel;

    ProgressDialog loading;
    private static final String TAG = AddHotelActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://cegephotel.com/";
    private Uri uri;
    RatingBar rv_rating;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hotel);
        getSupportActionBar().setTitle("Update Hotel Information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etHotelName=(EditText)findViewById(R.id.etHotelName);
        etLocation=(EditText)findViewById(R.id.etLocation);
        etAbout=(EditText)findViewById(R.id.etAbout);
        rv_rating=(RatingBar)findViewById(R.id.rv_rating);

        etHotelName.setText(getIntent().getStringExtra("hname"));
        etLocation.setText(getIntent().getStringExtra("location"));
        etAbout.setText(getIntent().getStringExtra("about"));
        rv_rating.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));

        btnUpdateHotel=(Button)findViewById(R.id.btnUpdateHotel);
        btnUpdateHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHotel();
            }
        });
    }
    public  void updateHotel() {

        String hotel_id=getIntent().getStringExtra("hid");
        loading= new ProgressDialog(EditHotelActivity.this);
        loading.setTitle("Please wait,Hotel Data is being updating...");
        loading.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.updatehotel(etHotelName.getText().toString(),
                etLocation.getText().toString(),
                etAbout.getText().toString(),
                String.valueOf(rv_rating.getRating()),
                hotel_id);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                loading.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(EditHotelActivity.this, AdminHomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(EditHotelActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(EditHotelActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}