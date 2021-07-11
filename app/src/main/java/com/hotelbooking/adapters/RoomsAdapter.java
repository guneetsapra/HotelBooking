package com.hotelbooking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.hotelbooking.GuestUserHotelDetailsActivity;
import com.hotelbooking.R;
import com.hotelbooking.model.HotelPojo;
import com.hotelbooking.model.RoomsPojo;

import java.util.List;

public class RoomsAdapter extends BaseAdapter {
    List<RoomsPojo> hotelInfo;
    Context con;
    String url= "http://cegephotel.com/hotel/";

    public RoomsAdapter(List<RoomsPojo> hotelInfo, Context con) {
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
        View rooms = obj1.inflate(R.layout.childroom, null);

        ImageView roomimage=(ImageView)rooms.findViewById(R.id.roomimage);
        Glide.with(con).load(url+hotelInfo.get(pos).getImage()).into(roomimage);


        TextView tvtype = (TextView) rooms.findViewById(R.id.tvtype);
        tvtype.setText(hotelInfo.get(pos).getType());

        TextView tvprice = (TextView) rooms.findViewById(R.id.tvprice);
        tvprice.setText(hotelInfo.get(pos).getPrice()+"$");

        return rooms;
    }

}