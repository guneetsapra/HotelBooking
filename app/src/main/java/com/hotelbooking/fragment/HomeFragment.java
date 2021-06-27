package com.hotelbooking.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hotelbooking.R;
import com.hotelbooking.adapters.UserHomeHotelListAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.HotelPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    View view;

    List<HotelPojo> hotelInfo;
    ListView list_hotels;
    ProgressDialog loading;

    UserHomeHotelListAdapter userHomeHotelListAdapter;

    public static HomeFragment homeFragment() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

        list_hotels = (ListView) view.findViewById(R.id.list_hotels);


        hotelInfo = new ArrayList<>();
        getHotelList();

        return view;
    }
    public void getHotelList() {
        loading = new ProgressDialog(getContext());
        loading.setMessage("Hotels Loading....");
        loading.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<HotelPojo>> call = service.gethotel();
        call.enqueue(new Callback<List<HotelPojo>>() {
            @Override
            public void onResponse(Call<List<HotelPojo>> call, Response<List<HotelPojo>> response) {
                loading.dismiss();
                if (response.body() == null) {
                    Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    userHomeHotelListAdapter=new UserHomeHotelListAdapter(hotelInfo,getContext());
                    list_hotels.setAdapter(userHomeHotelListAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<HotelPojo>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



