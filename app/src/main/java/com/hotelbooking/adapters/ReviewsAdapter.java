package com.hotelbooking.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hotelbooking.AdminHomeActivity;
import com.hotelbooking.EditHotelActivity;
import com.hotelbooking.R;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.HotelPojo;
import com.hotelbooking.model.ResponseData;
import com.hotelbooking.model.ReviewPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsAdapter extends BaseAdapter {
    List<ReviewPojo> reviewPojo;
    Context con;
    String url= "http://cegephotel.com/hotel/";

    public ReviewsAdapter(List<ReviewPojo> reviewPojo, Context con) {
        this.reviewPojo = reviewPojo;
        this.con = con;
    }

    @Override
    public int getCount() {
        return reviewPojo.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup) {
        LayoutInflater obj1 = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View reviews = obj1.inflate(R.layout.childreview, null);

        TextView tvusername = (TextView) reviews.findViewById(R.id.tvusername);
        tvusername.setText(reviewPojo.get(pos).getName());

        TextView tvmsg = (TextView) reviews.findViewById(R.id.tvmsg);
        tvmsg.setText(reviewPojo.get(pos).getMsg());

        RatingBar rv_rating= (RatingBar)reviews.findViewById(R.id.getrv_rating);
        rv_rating.setRating(Float.parseFloat(reviewPojo.get(pos).getRating()));
        return reviews;
    }

}