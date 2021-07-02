package com.hotelbooking.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

public class SearchFragment extends Fragment {

    View view;

    List<HotelPojo> hotelInfo;
    ListView list_hotels;
    ProgressDialog loading;
    EditText search;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");
        search = (EditText) view.findViewById(R.id.et_search);
        list_hotels = (ListView) view.findViewById(R.id.list_hotels);


        hotelInfo = new ArrayList<>();
        getHotelList();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                userHomeHotelListAdapter.searchHotel(text);

            }
        });
        return view;
    }

    public void getHotelList() {
    }
}



