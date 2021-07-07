package com.hotelbooking.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hotelbooking.R;
import com.hotelbooking.Utils;
import com.hotelbooking.adapters.MyBookingsAdapter;
import com.hotelbooking.adapters.UserHomeHotelListAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.BookingsPojo;
import com.hotelbooking.model.HotelPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingsFragment extends Fragment {

    View view;

    List<BookingsPojo> hotelInfo;
    ListView list_bookings;
    ProgressDialog loading;
    SharedPreferences sharedPreferences;
    MyBookingsAdapter myBookingsAdapter;

    public static MyBookingsFragment myBookingsFragment() {
        MyBookingsFragment fragment = new MyBookingsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_mybookings, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

        list_bookings = (ListView) view.findViewById(R.id.list_bookings);


        hotelInfo = new ArrayList<>();
        getMyBookings();

        return view;
    }
    public void getMyBookings() {
        sharedPreferences = getActivity().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        String sessionname = sharedPreferences.getString("user_name", "def-val");

        loading = new ProgressDialog(getContext());
        loading.setMessage("My Bookings Loading");
        loading.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<BookingsPojo>> call = service.mybookings(sessionname);
        call.enqueue(new Callback<List<BookingsPojo>>() {
            @Override
            public void onResponse(Call<List<BookingsPojo>> call, Response<List<BookingsPojo>> response) {
                loading.dismiss();
                if (response.body() == null) {
                    Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    myBookingsAdapter=new MyBookingsAdapter(hotelInfo,getContext());
                    list_bookings.setAdapter(myBookingsAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<BookingsPojo>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

