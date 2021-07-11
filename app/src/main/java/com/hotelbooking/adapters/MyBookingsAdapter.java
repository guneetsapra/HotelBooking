package com.hotelbooking.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.hotelbooking.AdminHomeActivity;
import com.hotelbooking.AdminViewReviewsActivity;
import com.hotelbooking.EditHotelActivity;
import com.hotelbooking.FullReciptActivity;
import com.hotelbooking.R;
import com.hotelbooking.UserHomeActivity;
import com.hotelbooking.Utils;
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
    SharedPreferences sharedPreferences;
    String uname;


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
                Intent intent=new Intent(con, FullReciptActivity.class);
                intent.putExtra("bid",bookingsPojo.get(pos).getBid());
                intent.putExtra("hid",bookingsPojo.get(pos).getHid());
                intent.putExtra("hotel_name",bookingsPojo.get(pos).getHotel_name());
                intent.putExtra("location",bookingsPojo.get(pos).getLocation());
                intent.putExtra("rating",bookingsPojo.get(pos).getRating());

                intent.putExtra("pic",bookingsPojo.get(pos).getImage());
                intent.putExtra("name",bookingsPojo.get(pos).getName());
                intent.putExtra("age",bookingsPojo.get(pos).getAge());
                intent.putExtra("bdate",bookingsPojo.get(pos).getBdate());
                intent.putExtra("room",bookingsPojo.get(pos).getRoom());


                intent.putExtra("days",bookingsPojo.get(pos).getDays());
                intent.putExtra("payment",bookingsPojo.get(pos).getPayment());
                intent.putExtra("email",bookingsPojo.get(pos).getEmail());
                intent.putExtra("status",bookingsPojo.get(pos).getStatus());

                con.startActivity(intent);

            }
        });

        sharedPreferences = con.getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname=sharedPreferences.getString("user_name","");

        if(bookingsPojo.get(pos).getStatus().equals("Booked"))
        {
            Button btncancel=(Button)bookings.findViewById(R.id.btncancel);
            btncancel.setVisibility(View.VISIBLE);
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancelbooking(bookingsPojo.get(pos).getBid(),uname);
                }
            });
        }



        return bookings;
    }

    ProgressDialog progressDialog;
    public void cancelbooking(String bid, String email){
        progressDialog = new ProgressDialog(con);
        progressDialog.setMessage("Processing please wait");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.cancelBooking(bid,email);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(con,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(con, UserHomeActivity.class);
                    con.startActivity(intent);
                    Toast.makeText(con,"Cancelled successfully",Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(con, "Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}