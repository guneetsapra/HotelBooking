package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteReviewActivity extends AppCompatActivity {

    ImageView getimage;
    TextView tvhname;
    EditText etname,etreview;
    RatingBar rv_rating;
    Button btn_submit;
    SharedPreferences sharedPreferences;
    String session;
    String hotel_url;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        getSupportActionBar().setTitle("Write Review");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "def-val");
        getimage=(ImageView)findViewById(R.id.himage);
       // Toast.makeText(WriteReviewActivity.this, getIntent().getStringExtra("photo"), Toast.LENGTH_SHORT).show();

        hotel_url = getIntent().getStringExtra("photo");
        Glide.with(getApplicationContext()).load(hotel_url).into(getimage);

        tvhname=(TextView)findViewById(R.id.tvhname);
        tvhname.setText(getIntent().getStringExtra("hname"));
        etname=(EditText) findViewById(R.id.etname);
        etreview=(EditText)findViewById(R.id.etreview);
        rv_rating=(RatingBar)findViewById(R.id.rv_rating);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etname.getText().toString().isEmpty()){
                    Toast.makeText(WriteReviewActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etreview.getText().toString().isEmpty()){
                    Toast.makeText(WriteReviewActivity.this, "Please Enter your comment", Toast.LENGTH_SHORT).show();
                    return;
                }
                //  startActivity(new Intent(AdminLoginActivity.this, AdminHomeActivity.class));

                submitReview();
            }
        });

    }


    private void submitReview() {
    }
}