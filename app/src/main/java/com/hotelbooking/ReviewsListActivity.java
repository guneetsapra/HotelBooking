package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelbooking.adapters.ReviewsAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.ReviewPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsListActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_reviews_list);

        getSupportActionBar().setTitle("Reviews and ratings");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hotel_reviews=(ListView)findViewById(R.id.hotel_reviews);
        hotelInfo = new ArrayList<>();
        getReviews(getIntent().getStringExtra("hid"));
    }

    public void getReviews(final String hid) {
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