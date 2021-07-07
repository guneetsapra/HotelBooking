package com.hotelbooking.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelbooking.R;
import com.hotelbooking.UserHomeActivity;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.fragment.HomeFragment;
import com.hotelbooking.fragment.MyBookingsFragment;
import com.hotelbooking.model.BookingsPojo;
import com.hotelbooking.model.ResponseData;
import com.hotelbooking.model.ReviewPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingsAdapter  extends BaseAdapter {
    List<BookingsPojo> bookingsPojo;
    Context con;


    public MyBookingsAdapter(List<BookingsPojo> bookingsPojo, Context con) {
        this.bookingsPojo = bookingsPojo;
        this.con = con;
    }

    @Override
    public int getCount() {
        return bookingsPojo.size();
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
        View bookings = obj1.inflate(R.layout.childmybookings, null);

        TextView tvusername = (TextView) bookings.findViewById(R.id.tvusername);
        tvusername.setText(bookingsPojo.get(pos).getHotel_name());

        TextView tvmsg = (TextView) bookings.findViewById(R.id.tvmsg);
        tvmsg.setText(bookingsPojo.get(pos).getLocation());

        TextView tvbdate = (TextView) bookings.findViewById(R.id.tvbdate);
        tvbdate.setText(bookingsPojo.get(pos).getBdate());

        TextView tvstatus = (TextView) bookings.findViewById(R.id.tvstatus);
        tvstatus.setText("Status : "+ bookingsPojo.get(pos).getStatus());

        TextView tvreceipt = (TextView) bookings.findViewById(R.id.tvreceipt);
        tvreceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(con,"Comming soon..",Toast.LENGTH_SHORT).show();
            }
        });

        if(bookingsPojo.get(pos).getStatus().equals("Booked"))
        {
            Button btncancel=(Button)bookings.findViewById(R.id.btncancel);
            btncancel.setVisibility(View.VISIBLE);
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancelbooking(bookingsPojo.get(pos).getBid());
                }
            });
        }



        return bookings;
    }

}