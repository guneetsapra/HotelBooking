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
        loading = new ProgressDialog(ReviewsListActivity.this);
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
                    Toast.makeText(ReviewsListActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    hotel_reviews.setAdapter(new ReviewsAdapter(hotelInfo, ReviewsListActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<ReviewPojo>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(ReviewsListActivity.this, "Something went wrong...Please contact admin !", Toast.LENGTH_SHORT).show();
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