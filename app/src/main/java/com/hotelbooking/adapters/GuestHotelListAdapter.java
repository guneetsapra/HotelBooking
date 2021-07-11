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

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.hotelbooking.AdminHomeActivity;
import com.hotelbooking.EditHotelActivity;
import com.hotelbooking.GuestUserHotelDetailsActivity;
import com.hotelbooking.HotelDetailsActivity;
import com.hotelbooking.R;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.HotelPojo;
import com.hotelbooking.model.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestHotelListAdapter extends BaseAdapter {
    List<HotelPojo> hotelInfo;
    Context con;
    String url= "http://cegephotel.com/hotel/";

    public GuestHotelListAdapter(List<HotelPojo> hotelInfo, Context con) {
        this.hotelInfo = hotelInfo;
        this.con = con;
    }

    @Override
    public int getCount() {
        return hotelInfo.size();
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
        View hotels = obj1.inflate(R.layout.adapter_geusthotellist, null);

        ImageView hotelImage=(ImageView)hotels.findViewById(R.id.image);
        Glide.with(con).load(url+hotelInfo.get(pos).getImage()).into(hotelImage);

        Toast.makeText(con,hotelInfo.get(pos).getImage().toString(),Toast.LENGTH_LONG).show();

        TextView tvHotelname = (TextView) hotels.findViewById(R.id.tvHotelname);
        tvHotelname.setText(hotelInfo.get(pos).getHotel_name());

        TextView tvLocation = (TextView) hotels.findViewById(R.id.tvLocation);
        tvLocation.setText(hotelInfo.get(pos).getLocation());

        RatingBar rv_rating= (RatingBar)hotels.findViewById(R.id.rv_rating);
        rv_rating.setRating(Float.parseFloat(hotelInfo.get(pos).getRating()));

        CardView cd_hotel= (CardView)hotels.findViewById(R.id.cd_hotel);
        cd_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(con, GuestUserHotelDetailsActivity.class);
                intent.putExtra("hid",hotelInfo.get(pos).getHid());
                intent.putExtra("image",url+hotelInfo.get(pos).getImage());
                intent.putExtra("hname",hotelInfo.get(pos).getHotel_name());
                intent.putExtra("location",hotelInfo.get(pos).getLocation());
                intent.putExtra("about",hotelInfo.get(pos).getAbout());
                intent.putExtra("rating",hotelInfo.get(pos).getRating());
                con.startActivity(intent);
            }
        });

        return hotels;
    }

}