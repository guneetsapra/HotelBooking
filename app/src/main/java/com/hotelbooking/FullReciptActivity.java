package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FullReciptActivity extends AppCompatActivity {

    TextView tv_bid,tv_hotelname,tv_bdate,tv_status,tv_name,tv_email,tv_age,tv_room,tv_days,tv_payment;
    ImageView getimage;
    String url= "http://cegephotel.com/hotel/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_recipt);
        getSupportActionBar().setTitle("Booking Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_bid=(TextView)findViewById(R.id.tv_bid);
        tv_hotelname=(TextView)findViewById(R.id.tv_hotelname);
        tv_bdate=(TextView)findViewById(R.id.tv_bdate);
        tv_status=(TextView)findViewById(R.id.tv_status);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_email=(TextView)findViewById(R.id.tv_email);
        tv_age=(TextView)findViewById(R.id.tv_age);
        tv_room=(TextView)findViewById(R.id.tv_room);
        tv_days=(TextView)findViewById(R.id.tv_days);
        tv_payment=(TextView)findViewById(R.id.tv_payment);

        getimage=(ImageView)findViewById(R.id.getimage);


        getimage=(ImageView)findViewById(R.id.getimage);
        Glide.with(getApplicationContext()).load(url+getIntent().getStringExtra("pic")).into(getimage);

        tv_bid.setText("Booking RefID# : "+getIntent().getStringExtra("bid"));
        tv_hotelname.setText(getIntent().getStringExtra("hotel_name"));
        tv_bdate.setText(getIntent().getStringExtra("bdate"));

        tv_status.setText(getIntent().getStringExtra("status"));
        tv_name.setText("Name : "+getIntent().getStringExtra("name"));
        tv_email.setText("Email : "+getIntent().getStringExtra("email"));
        tv_age.setText("Age : "+getIntent().getStringExtra("age"));
        tv_room.setText("Room Type : "+getIntent().getStringExtra("room"));
        tv_days.setText("No Of Days : "+getIntent().getStringExtra("days"));
        tv_payment.setText("Mode Of Payment : "+getIntent().getStringExtra("payment"));

//        intent.putExtra("bid",bookingsPojo.get(pos).getBid());
//        intent.putExtra("hid",bookingsPojo.get(pos).getHid());
//        intent.putExtra("hotel_name",bookingsPojo.get(pos).getHotel_name());
//        intent.putExtra("location",bookingsPojo.get(pos).getLocation());
//        intent.putExtra("rating",bookingsPojo.get(pos).getRating());
//
//        intent.putExtra("image",bookingsPojo.get(pos).getImage());
//        intent.putExtra("name",bookingsPojo.get(pos).getName());
//        intent.putExtra("age",bookingsPojo.get(pos).getAge());
//        intent.putExtra("bdate",bookingsPojo.get(pos).getBdate());
//        intent.putExtra("room",bookingsPojo.get(pos).getRoom());
//
//
//        intent.putExtra("days",bookingsPojo.get(pos).getDays());
//        intent.putExtra("payment",bookingsPojo.get(pos).getPayment());
//        intent.putExtra("email",bookingsPojo.get(pos).getEmail());
//        intent.putExtra("status",bookingsPojo.get(pos).getStatus());
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