package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
    Button btnbook,btn_write;
    ListView hotel_reviews;
    List<ReviewPojo> hotelInfo;
    ProgressDialog loading;
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

        hotel_reviews=(ListView)findViewById(R.id.hotel_reviews);
        hotelInfo = new ArrayList<>();
        getReviews(getIntent().getStringExtra("hid"));

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
                Uri uriSms = Uri.parse("smsto:1234567899");
                Intent intentSMS = new Intent(Intent.ACTION_SENDTO, uriSms);
                intentSMS.putExtra("sms_body", "The SMS text");
                startActivity(intentSMS);
            }
        });

        imgcall=(ImageView)findViewById(R.id.imgcall);
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+123));//change the number
                startActivity(callIntent);
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

    }
}