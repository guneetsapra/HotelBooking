package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hotelbooking.adapters.AdminHotelListAdapter;
import com.hotelbooking.adapters.ReviewsAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.HotelPojo;
import com.hotelbooking.model.ReviewPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailsActivity extends AppCompatActivity {


    TextView tvhotelname, tvlocation,tvabout;
    RatingBar rv_rating;
    ImageView getimage, imgmsg, imgcall;
    Button btnbook,btn_write,btnrooms;
    ListView hotel_reviews;
    List<ReviewPojo> hotelInfo;
    ProgressDialog loading;
    SharedPreferences sharedPreferences;
    String sessionemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        getSupportActionBar().setTitle("Hotel Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvhotelname=(TextView)findViewById(R.id.tvhname);
        tvlocation=(TextView)findViewById(R.id.tvlocation);
        tvabout=(TextView)findViewById(R.id.tvdescription);
        rv_rating=(RatingBar)findViewById(R.id.rv_rating);
        sharedPreferences = getApplicationContext().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        sessionemail = sharedPreferences.getString("user_name", "def-val");

        hotel_reviews=(ListView)findViewById(R.id.hotel_reviews);
        hotelInfo = new ArrayList<>();
        getReviews(getIntent().getStringExtra("hid"));

        btnrooms=(Button)findViewById(R.id.btnrooms);
        btnrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HotelDetailsActivity.this, ViewRoomsActivity.class);
                i.putExtra("hid",getIntent().getStringExtra("hid"));
                startActivity(i);
            }
        });

        btn_write=(Button)findViewById(R.id.btn_write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HotelDetailsActivity.this, WriteReviewActivity.class);
                i.putExtra("hid",getIntent().getStringExtra("hid"));
                i.putExtra("hname",getIntent().getStringExtra("hname"));
                i.putExtra("photo",getIntent().getStringExtra("image"));
                startActivity(i);
            }
        });

        btnbook=(Button)findViewById(R.id.btnbook);
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HotelDetailsActivity.this, BookHotelActivity.class);
                i.putExtra("hid",getIntent().getStringExtra("hid"));
                startActivity(i);
            }
        });

        imgmsg=(ImageView)findViewById(R.id.imgmsg);
        imgmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri uriSms = Uri.parse("smsto:1234567899");
//                Intent intentSMS = new Intent(Intent.ACTION_SENDTO, uriSms);
//                intentSMS.putExtra("sms_body", "The SMS text");
//                startActivity(intentSMS);

                Intent intent=new Intent(HotelDetailsActivity.this, ChatHotelActivity.class);
                intent.putExtra("hid",getIntent().getStringExtra("hid"));
                intent.putExtra("from",sessionemail);
                intent.putExtra("to","admin@gmail.com");
                startActivity(intent);
            }
        });

        imgcall=(ImageView)findViewById(R.id.imgcall);
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "+512667788";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        getimage=(ImageView)findViewById(R.id.getimage);
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image")).into(getimage);
        tvhotelname.setText(getIntent().getStringExtra("hname"));
        tvlocation.setText(getIntent().getStringExtra("location"));
        tvabout.setText(getIntent().getStringExtra("about"));
        rv_rating.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));
    }

    public void getReviews(final String hid) {
        loading = new ProgressDialog(HotelDetailsActivity.this);
        loading.setMessage("Loading....");
        loading.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<ReviewPojo>> call = service.getreviews(hid);
        call.enqueue(new Callback<List<ReviewPojo>>() {
            @Override
            public void onResponse(Call<List<ReviewPojo>> call, Response<List<ReviewPojo>> response) {
                loading.dismiss();
               // Toast.makeText(HotelDetailsActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(HotelDetailsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    hotel_reviews.setAdapter(new ReviewsAdapter(hotelInfo, HotelDetailsActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<ReviewPojo>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(HotelDetailsActivity.this, "Something went wrong...Please contact admin !", Toast.LENGTH_SHORT).show();
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