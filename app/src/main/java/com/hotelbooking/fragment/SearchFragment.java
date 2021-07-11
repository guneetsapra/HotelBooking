package com.hotelbooking.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hotelbooking.R;
import com.hotelbooking.SearchHotelActivity;
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

public class SearchFragment extends Fragment {

    View view;

    List<HotelPojo> hotelInfo;
    ListView list_hotels;
    ProgressDialog loading;
    EditText search;
    Button btnsearch;
    UserHomeHotelListAdapter userHomeHotelListAdapter;

    public static SearchFragment searchFragment() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_search, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        search = (EditText)view.findViewById(R.id.et_search);
        list_hotels = (ListView) view.findViewById(R.id.list_hotels);

        btnsearch=(Button) view.findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), SearchHotelActivity.class);
                startActivity(i);
            }
        });

        hotelInfo = new ArrayList<>();
        getHotelList();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                userHomeHotelListAdapter.searchHotel(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });
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

