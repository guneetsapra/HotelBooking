package com.hotelbooking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hotelbooking.R;
import com.hotelbooking.model.HotelPojo;

import java.util.List;

public class UserHomeHotelListAdapter extends BaseAdapter {
    List<HotelPojo> hotelInfo;
    Context cnt;
    String url= "http://cegephotel.com/hotel/";
    public UserHomeHotelListAdapter(List<HotelPojo> hotelInfo, Context cnt) {
        this.hotelInfo = hotelInfo;
        this.cnt = cnt;
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
        LayoutInflater obj1 = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View hotels = obj1.inflate(R.layout.adapter_userhomehotellist, null);

        ImageView hotelImage=(ImageView)hotels.findViewById(R.id.image);
        Glide.with(cnt).load(url+hotelInfo.get(pos).getImage()).into(hotelImage);

        Toast.makeText(cnt,hotelInfo.get(pos).getImage().toString(),Toast.LENGTH_LONG).show();

        TextView tvHotelname = (TextView) hotels.findViewById(R.id.tvHotelname);
        tvHotelname.setText(hotelInfo.get(pos).getHotel_name());

        TextView tvLocation = (TextView) hotels.findViewById(R.id.tvLocation);
        tvLocation.setText(hotelInfo.get(pos).getLocation());

        RatingBar rv_rating= (RatingBar)hotels.findViewById(R.id.rv_rating);
        rv_rating.setRating(Float.parseFloat(hotelInfo.get(pos).getRating()));


        return hotels;
    }

}