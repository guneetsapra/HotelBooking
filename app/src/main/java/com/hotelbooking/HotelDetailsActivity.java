package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HotelDetailsActivity extends AppCompatActivity {


    TextView tvhotelname, tvlocation,tvabout;
    RatingBar rv_rating;
    ImageView getimage, imgmsg, imgcall;
    Button btnbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        tvhotelname=(TextView)findViewById(R.id.tvhname);
        tvlocation=(TextView)findViewById(R.id.tvlocation);
        tvabout=(TextView)findViewById(R.id.tvdescription);
        rv_rating=(RatingBar)findViewById(R.id.rv_rating);

        btnbook=(Button)findViewById(R.id.btnbook);
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HotelDetailsActivity.this, BookHotelActivity.class);
                startActivity(i);
            }
        });

        imgmsg=(ImageView)findViewById(R.id.imgmsg);
        imgmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgcall=(ImageView)findViewById(R.id.imgcall);
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        getimage=(ImageView)findViewById(R.id.getimage);
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image")).into(getimage);
        tvhotelname.setText(getIntent().getStringExtra("hname"));
        tvlocation.setText(getIntent().getStringExtra("location"));
        tvabout.setText(getIntent().getStringExtra("about"));
        rv_rating.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));
    }
}